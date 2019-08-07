package com.calibrage.a3ffarmerapp.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.calibrage.a3ffarmerapp.Activities.Downloader;
import com.calibrage.a3ffarmerapp.Activities.LoadingDialog;
import com.calibrage.a3ffarmerapp.Activities.PdfViewerActivity;
import com.calibrage.a3ffarmerapp.Adapters.FileAdapter;
import com.calibrage.a3ffarmerapp.Model.FileBean;
import com.calibrage.a3ffarmerapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.calibrage.a3ffarmerapp.util.UrlConstants.learing_videos_pdfs;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {

    ListView listView;
    String path;
    ArrayList<FileBean> list;
    FileAdapter adapter;
    public static String TAG = "PhotoFragment";
    private String embedUrl, idString, category, fileUrl, fileName;
    String[] strArray, categoryArray, fileUrlArray;
    private static final int REQUEST_PERMISSIONS = 101;
    File folder;
    String id;
    private ProgressDialog dialog;
    ArrayList<String> isPdfs = new ArrayList<>();
    TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        list = new ArrayList<>();
        // setTitle("Pdf Reader");
        dialog = new ProgressDialog(getActivity());
        text=(TextView)view.findViewById(R.id.text);
        //Check if permission is granted(for Marshmallow and higher versions)

            checkPermission();
        SharedPreferences pref = getContext().getSharedPreferences("DATA", MODE_PRIVATE);
        id=pref.getString("Id", "");       // Saving string data of your editext
        Log.d("PhotoFragment", "id2======" + id);
        //    folder = new File( Environment.getExternalStorageDirectory(), "KnowledgeZonePDF");
    //    String folderPath = Environment.getExternalStorageDirectory()+"/pathTo/folder";
        //get the absolute path of phone storage
     //   path = Environment.getExternalStorageDirectory().getAbsolutePath();
     //   path = Environment.getExternalStorageDirectory()+"/KnowledgeZonePDF";

        //calling the initList that will initialize the list to be given to Adapter for binding data


//        adapter = new FileAdapter(getContext(), R.layout.list_item, list);
//
//        //set the adapter on listView
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        listView.invalidateViews();
//        listView.refreshDrawableState();
        //when user chooses a particular pdf file from list,
        //start another activity that will show the pdf
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getContext(), PdfViewerActivity.class);
//                intent.putExtra("keyName", list.get(position).getFileName());
//                intent.putExtra("keyPath",list.get(position).getFilePath());
//                startActivity(intent);

                new    DownloadImageFile(getContext(),list.get(position).getFilePath(),list.get(position).getFileName(),".pdf",dialog).execute();
            }
        });
        getEncyclopedia();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());




        builder.detectFileUriExposure();
        isStoragePermissionGranted();
        return view;
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
    private void getEncyclopedia() {
        //  String id="APWGBDAB00010001";
        LoadingDialog.showLoadingDialog(getContext(), "Loading...");


        //    String Id = "1004";
        dialog.setMessage("Loading, please wait.....");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        String Id = "1004";

     //   String url = "http://183.82.111.111/3FFarmerAPI/api/Encyclopedia/GetFilesByCategory/" + id;

       String url =learing_videos_pdfs+id;

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "RESPONSE======" + response);
                LoadingDialog.cancelLoading();
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d(TAG, "RESPONSE Encyclopedia======" + jsonObject);

                    JSONArray alsoKnownAsArray = jsonObject.getJSONArray("listResult");
                    for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                        String alsoKnown = alsoKnownAsArray.getString(i);
                        JSONObject leagueData = alsoKnownAsArray.getJSONObject(i);
                        String fileType = leagueData.getString("fileType");
                        embedUrl = leagueData.getString("embedUrl");
                        category = leagueData.getString("category");
                        fileUrl = leagueData.getString("fileUrl");
                        fileName = leagueData.getString("name");

                        // Log.v("TAG --fileName ", fileName);

                        /*if (fileType.equals("Video")){

                            Log.v("kiran", embedUrl);
                            if (embedUrl.equals("null")) {
                                Log.v("TAG --fileUrl ", fileUrl);

                            }else {
                                idString=embedUrl.substring(32);
                                strArray=new String[] {idString};
                                //
                                categoryArray=new String[]{category};

                                Log.v("TAG --govindha ", embedUrl);}


                        }*/
                        if (embedUrl.equals("null")) {
                            Log.v("TAG --fileUrl ", fileUrl);
                            Log.v("no videos", embedUrl);
                            isPdfs.add("");
                        }else{

                        }
                        if (fileType.equals("PDF")) {
                            if (embedUrl.equals("null")) {
                                Log.v("TAG --fileUrl ", fileType);
                                Log.v("TAG --fileUrl ", fileUrl);
                                Log.v("TAG --fileName ", fileName);
                                //     path = Environment.getExternalStorageDirectory().getAbsolutePath();
                          //   new    DownloadImageFile(getContext(),fileUrl,fileName,".pdf").execute();

                                list.add(new FileBean(fileName, fileUrl));
                                adapter = new FileAdapter(getContext(), R.layout.list_item, list);

                                //set the adapter on listView
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.invalidateViews();
                                listView.refreshDrawableState();

                                //calling the initList that will initialize the list to be given to Adapter for binding data
                                //   initList(fileName,fileUrl);
                              /*  String extStorageDirectory = Environment.getExternalStorageDirectory()
                                        .toString();
                                File folder = new File(extStorageDirectory, "pdf");

                           //     folder.mkdir();
                                File file = new File(folder, "Read.pdf");
                                try {
                                    file.createNewFile();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }*/

                                //Downloader.DownloadFile(fileUrl, file);

                                //showPdf();
                            }
                        }

                    }
                    if(isPdfs.size()>0){
                        listView.setVisibility(View.VISIBLE);
                        text.setVisibility(View.GONE);
                    }else{
                        listView.setVisibility(View.GONE);
                        text.setVisibility(View.VISIBLE);
                    }
                    //   Log.d(TAG,"RESPONSE Encyclopedia jsonArray======"+ jsonArray);

                  //  initList(path);
                    String success = jsonObject.getString("isSuccess");
                    Log.d(TAG, "success Encyclopedia======" + success);
                    if (success.equals("true")) {
                       /* Intent intent =new Intent(getContext(), OtpActivity.class);
                        intent.putExtra ( "Farmer id", farmerId.getText().toString() );
                        startActivity(intent);*/
                        //   Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Invalid User", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (error instanceof NetworkError) {
                    Log.i("one:" + TAG, error.toString());
                    Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Log.i("two:" + TAG, error.toString());
                    Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Log.i("three:" + TAG, error.toString());
                    Toast.makeText(getContext(), "AuthFailure Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Log.i("four::" + TAG, error.toString());
                    Toast.makeText(getContext(), "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Log.i("five::" + TAG, error.toString());
                    Toast.makeText(getContext(), "NoConnection Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Log.i("six::" + TAG, error.toString());
                    Toast.makeText(getContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("Checking error in else");
                }
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void showPdf( File path) {
       // File file = new File(Environment.getExternalStorageDirectory() + path);
        PackageManager packageManager = getActivity().getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(path);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(intent);
    }

    //initializing the ArrayList
    void initList(String path){
        try{
            File file = new File(path);
            File[] fileArr = file.listFiles();
            String fileName;
            for(File file1 : fileArr){
                if(file1.isDirectory()){
                    initList(file1.getAbsolutePath());
                }else{
                    fileName = file1.getName();
                    //choose only the pdf files
                    if(fileName.endsWith(".pdf")){
                        list.add(new FileBean(fileName, file1.getAbsolutePath()));

                    }
                }

            }
        }catch(Exception e){
            Log.i("show","Something went wrong. "+e.toString());
        }
    }

    //Handling permissions for Android Marshmallow and above
    void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //if permission granted, initialize the views
          //  initViews();
        } else {
            //show the dialog requesting to grant permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   // initViews();
                } else {
                    //permission is denied (this is the first time, when "never ask again" is not checked)
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        getActivity().finish();
                    }
                    //permission is denied (and never ask again is  checked)
                    else {
                        //shows the dialog describing the importance of permission, so that user should grant
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("You have forcefully denied Read storage permission.\n\nThis is necessary for the working of app." + "\n\n" + "Click on 'Grant' to grant permission")
                                //This will open app information where user can manually grant requested permission
                                .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        getActivity().finish();
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts("package", getActivity().getPackageName(), null));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                })
                                //close the app
                                .setNegativeButton("Don't", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        getActivity().finish();
                                    }
                                });
                        builder.setCancelable(false);
                        builder.create().show();
                    }
                }
        }
    }


    private class DownloadImageFile extends AsyncTask<String, Void, Void> {

        private Context context;
        private String path;
        private String fileName;
        private String extension;
        File pdfFile;
        private ProgressDialog dialog;
        private DownloadImageFile(Context context, String path, String fileName, String extension,ProgressDialog progressBar) {
            this.context = context;
            this.path = path;
            this.fileName = fileName;
            this.extension = extension;
            this.dialog = progressBar;

        }
        public boolean isExternalStorageWritable() {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                return true;
            }
            return false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {
                        //  adapter.notifyDataSetChanged();
                        //Toast.makeText(context, "File exists in Folder", Toast.LENGTH_SHORT).show();

                        if(dialog!=null){
                            dialog.setMessage("downloading, please wait.....");
                            dialog.show();
                            dialog.setCanceledOnTouchOutside(false);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        protected Void doInBackground(String... strings) {
            //showDialogAsk(getActivity(), "Downloading File");
            String fileUrl = path;   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = this.fileName;  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
             folder = new File(extStorageDirectory, "KnowledgeZonePDF");
            folder.mkdir();




            if (!folder.exists()) {
                folder.mkdir();
            }

             pdfFile = new File(folder, fileName + extension);


            if (!pdfFile.exists()) {
                try {
                    pdfFile.createNewFile();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                DownloadFile(fileUrl, pdfFile);
            } else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                          //  adapter.notifyDataSetChanged();
                            //Toast.makeText(context, "File exists in Folder", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

       //     showPdf(pdfFile);
         //  adapter.notifyDataSetChanged();
        /*    arraylist.clear();
            arraylist.addAll(db.readAll());*/
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {
                        if(dialog!=null&&dialog.isShowing()){
                            dialog.dismiss();
                        }
                        //  adapter.notifyDataSetChanged();
                        //Toast.makeText(context, "File exists in Folder", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
//            Intent intent = new Intent(getContext(), PdfViewerActivity.class);
//            intent.putExtra("keyName", fileName+".pdf");
//            intent.putExtra("keyPath",pdfFile);
//            startActivity(intent);
            showPdf(pdfFile);
        }




    }

    public static void DownloadFile(String fileURL, File directory) {
        try {

            String file_test=fileURL;
            String  a = "\'";

            String replaceString=file_test.replaceAll("\\\\", "/");
            FileOutputStream f = new FileOutputStream(directory);
            URL u = new URL(replaceString);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
          //  c.setRequestMethod("GET");
           // c.setDoOutput(true);
            c.connect();

            InputStream in = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

