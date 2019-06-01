package com.example.rhona.tundaapp;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Manifest;

import javax.net.ssl.HttpsURLConnection;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class AddProduct extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 1;

    Spinner category;
    Button camera,submit;
    ImageView pic;
    EditText pdt, price, qty, desc;
    private static final int CAMERA_REQUEST = 1888;
    String  prod_price="price";
    String prod_qty="quantity";
    String prod_desc="description";
    String prod_category="category";
    String prod = "product_name";
    String image_path = "image";
    String idtoserver = "user_seller_id";
    public Uri outputUriCamera, outputImageUri;
    String picture_folder = "TundaImages";
    String pnameToserver,pricetoserver,descriptiontoserver,categorytoserver,quantitytoserver;
    ProgressDialog progressDialog ;

    //new
    public  static final int RequestPermissionCode  = 1 ;
    public  static final int OpenCamera  = 7 ;
    Bitmap bitmap;
    boolean check = true;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //Edit text
        pdt = (EditText)findViewById(R.id.pdtname);
        price = (EditText)findViewById(R.id.price);
        qty = (EditText)findViewById(R.id.stock);
        desc = (EditText)findViewById(R.id.description);


        //spinner
        category = (Spinner) this.findViewById(R.id.categorylist);

        //imageview
        pic = (ImageView) this.findViewById(R.id.camera);

        //button
        camera = (Button) this.findViewById(R.id.takepic);
        //EnableRuntimePermissionToAccessCamera();

        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AddProduct.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                    File filePicture = new File(Environment.getExternalStorageDirectory(), picture_folder);
                    if (!filePicture.exists())
                        filePicture.mkdirs();
                    outputUriCamera = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), picture_folder + "/mypicture.jpg"));
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "mypicture.jpg");
                    values.put(MediaStore.Images.Media.DESCRIPTION, picture_folder);
                    outputImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    startActivityForResult(new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                    .putExtra(MediaStore.EXTRA_OUTPUT, outputImageUri), OpenCamera);


                }else {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(AddProduct.this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                        new AlertDialog.Builder(AddProduct.this)
                                .setTitle("Permission needed")
                                .setMessage("This permission is needed for the camera")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(AddProduct.this,
                                                new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .create().show();

                    } else{

                        ActivityCompat.requestPermissions(AddProduct.this,
                                new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

                    }
                }


            }
        });

        submit=(Button)findViewById(R.id.submitbutton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pnameToserver = pdt.getText().toString();
                pricetoserver = price.getText().toString();
                quantitytoserver = qty.getText().toString();
                descriptiontoserver = desc.getText().toString();
                categorytoserver = category.getSelectedItem().toString();


                ImageUploadToServerFunction();

//
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ImageUploadToServerFunction() {
        // Upload captured image online on server function.

            ByteArrayOutputStream byteArrayOutputStreamObject ;

            byteArrayOutputStreamObject = new ByteArrayOutputStream();

            // Converting bitmap image to jpeg format, so by default image will upload in jpeg format.
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

            byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

            final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

            class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

                @Override
                protected void onPreExecute() {

                    super.onPreExecute();

                    // Showing progress dialog at image upload time.
                    Log.e("Pre-Execute", "Start loading");
                    progressDialog = ProgressDialog.show(AddProduct.this, "Uploading your information", "Please Wait", false, false);
                }

                @Override
                protected void onPostExecute(String string1) {

                    super.onPostExecute(string1);

                    // Dismiss the progress dialog after done uploading.
                    progressDialog.dismiss();

                    // Printing uploading success message coming from server on android app.
                    Toast.makeText(AddProduct.this, string1, Toast.LENGTH_LONG).show();

                    // Setting image as transparent after done uploading.
                    pic.setImageResource(android.R.color.transparent);

                    //going to another intent
                    Intent intent = new Intent(AddProduct.this, SellerHome.class);
                    startActivity(intent);
                    finish();

                }

                @Override
                protected String doInBackground(Void... params) {
                    Log.e("Do In Background", "Loading...");
                    ImageProcessClass imageProcessClass = new ImageProcessClass();
                    SharedPrefManager sp= new SharedPrefManager(AddProduct.this);
                    User u=sp.getUser();
                    int uid=u.getId();

                    HashMap<String,String> HashMapParams = new HashMap<String,String>();
                    HashMapParams.put(idtoserver,""+uid);
//                    Log.e("id",SharedPrefManager.KEY_EMAIL);
                    HashMapParams.put(prod, pnameToserver);
                    HashMapParams.put(prod_price, pricetoserver);
                    HashMapParams.put(prod_desc, descriptiontoserver);
                    HashMapParams.put(prod_category, categorytoserver);
                    HashMapParams.put(prod_qty, quantitytoserver);

                    HashMapParams.put(image_path, ConvertImage);

                    String FinalData = imageProcessClass.ImageHttpRequest(URLs.URL_UPLOADIMAGE, HashMapParams);
                    Log.e("Result:", FinalData);
                    return FinalData;
                }
            }

            AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
            AsyncTaskUploadClassOBJ.execute();
        }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == CAMERA_REQUEST) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            pic.setImageBitmap(photo);
//        }

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OpenCamera && resultCode == RESULT_OK) {
            try {
                // Adding captured image in bitmap.
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), outputImageUri);
                // adding captured image in imageview.
                pic.setImageBitmap(bitmap);
            } catch (Exception r){
                Toast.makeText(getApplicationContext(), "Data is null", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No image taken", Toast.LENGTH_SHORT).show();
        }

    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            Log.e("Request", "Start");
            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);
                Log.e("URL:", ""+requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(50000);

                httpURLConnectionObject.setConnectTimeout(50000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {
                    Log.e("Connection: ", "OK");
                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){
                        Log.e("Buffered: ", "Loop");
                        stringBuilder.append(RC2);
                    }
                } else {
                    Log.e("Connection error", "--"+httpURLConnectionObject.getResponseCode());
                }

            } catch (Exception e) {
                Log.e("Do In Background", "Exception...");
                e.printStackTrace();
                stringBuilder.append("Network is Unreachable!!!");

            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }

}
