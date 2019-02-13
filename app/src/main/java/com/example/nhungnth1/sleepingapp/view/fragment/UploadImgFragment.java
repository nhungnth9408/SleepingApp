package com.example.nhungnth1.sleepingapp.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhungnth1.sleepingapp.R;
import com.example.nhungnth1.sleepingapp.presenter.ValidationPresenter;
import com.example.nhungnth1.sleepingapp.view.mvpview.UploadImgView;
import com.example.nhungnth1.sleepingapp.view.mvpview.UploadService;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class UploadImgFragment extends Fragment implements UploadImgView{
    public static final String BASE_URL = "http://test.toidicode.com/";
    public final static int PICK_IMAGE_REQUEST = 1;
    public final static int READ_EXTERNAL_REQUEST = 2;
    private static final int RESULT_OK = -1;
    private ProgressDialog mProgressDialog;
    @BindView(R.id.text_response)
    TextView mTextResponse;
    @BindView(R.id.imv)
    ImageView mImv;
    private Context mContext;
    public UploadImgFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_img, container, false);
        Log.i("Lifecycle", "onCreateView" + view);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void requestPermissionAndPickImage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            pickImage();
            return;
        }
        // Các bạn nhớ request permison cho các máy M trở lên nhé, k là crash ngay đấy.
        int result = ContextCompat.checkSelfPermission(mContext,
                READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {
            requestPermissions(new String[]{
                    READ_EXTERNAL_STORAGE}, READ_EXTERNAL_REQUEST);
        }
    }

    @OnClick(R.id.button_select_image)
    public void onClick() {
        requestPermissionAndPickImage();
    }

    public void pickImage() {
        // Gọi intent của hệ thống để chọn ảnh nhé.
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"),
                PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null &&
                data.getData() != null) {
            // Khi đã chọn xong ảnh thì chúng ta tiến hành upload thôi
            Uri uri = data.getData();
            // Get the path from the Uri
            final String path = getPathFromURI(uri);
            if (path != null) {
                File f = new File(path);
                uri = Uri.fromFile(f);
            }
            // Set the image in ImageView
            mImv.setImageURI(uri);
            uploadFiles(uri);
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public void uploadFiles(Uri uri) {
        if (uri == null) return;
        // Hàm call api sẽ mất 1 thời gian nên mình show 1 dialog nhé.
//        showProgress();
        File file = new File(getRealPathFromURI(uri));
        // Khởi tạo RequestBody từ file đã được chọn
        RequestBody requestBody = RequestBody.create(
                MediaType.parse(getActivity().getContentResolver().getType(uri)),
                file);
        // Trong retrofit 2 để upload file ta sử dụng Multipart, khai báo 1 MultipartBody.Part
        // uploaded_file là key mà mình đã định nghĩa trong khi khởi tạo server
        MultipartBody.Part filePart =
                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestBody);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        UploadService service = retrofit.create(UploadService.class);
        Call<ResponseBody> call = service.uploadFile(filePart);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response == null || response.body() == null) {
                    mTextResponse.setText(R.string.upload_media_false);
                    return;
                }
                try {
                    String responseUrl = response.body().string();
                    mTextResponse.setText(responseUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                dissmissDialog();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mTextResponse.setText(R.string.upload_media_false);
//                dissmissDialog();
            }
        });
    }

    private String getRealPathFromURI(Uri contentUri) {
//        String res = null;
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
//        if (cursor.moveToFirst()) {
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            res = cursor.getString(column_index + 1);
//        }
//        cursor.close();
//        return res;

        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(mContext, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}
