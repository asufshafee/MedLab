package com.mcc.medlabs.view.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.Objects.Shakha;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;
import com.medialablk.easytoast.EasyToast;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sehtak_Bill_Screen_1 extends Fragment {


    public Sehtak_Bill_Screen_1() {
        // Required empty public constructor
    }

    Shakha.SahtakBilDeniaObObject sahtakBilDeniaObObject;
    MyApplication myApplication;
    TextView Name;
    Dialog Progress;
    ImageView Image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sehtak__bill__screen_1, container, false);
//        ((MainActivity) getActivity()).Change_Tittle("HairLoss Checkups");

        Progress = AppUtils.LoadingSpinner(getActivity());
        sahtakBilDeniaObObject = (Shakha.SahtakBilDeniaObObject) getArguments().getSerializable("data");
        Name = (TextView) view.findViewById(R.id.Name);
        myApplication = (MyApplication) getActivity().getApplicationContext();
        WebView wv = (WebView) view.findViewById(R.id.Details);
        Image = view.findViewById(R.id.Image);


        if (!sahtakBilDeniaObObject.getImage().equals("")) {
            Picasso.with(getContext()).load(sahtakBilDeniaObObject.getImage()).into(Image);

        }

        ((MainActivity) getActivity()).ShowDownload_toolbar();

        ((MainActivity) getActivity()).findViewById(R.id.Download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions((Activity) getActivity(),
                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }


                if (sahtakBilDeniaObObject.getPDFLink().equals("")) {
                    EasyToast.error(getActivity(), getString(R.string.no_linl));
                } else {
                    File Directory = new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + "/download/");
                    Directory.mkdirs();

                    if (isFileExists("/download/" + sahtakBilDeniaObObject.getProgramName().replace(" ", "") + ".pdf")) {
                        File sdCard = Environment.getExternalStorageDirectory();
                        String filePath = sdCard.getAbsolutePath() + "/download/" + sahtakBilDeniaObObject.getProgramName().replace(" ", "") + ".pdf";
                        openPdf(filePath);
                    } else {

                        Progress.show();
                        DownloadFileTask task = new DownloadFileTask(
                                getActivity(),
                                "http://213.186.160.67:8086/MedlabsApp/" + sahtakBilDeniaObObject.getPDFLink(),
                                "/download/" + sahtakBilDeniaObObject.getProgramName().replace(" ", "") + ".pdf");
                        task.startTask();
                    }


                }
            }
        });


        ((MainActivity) getActivity()).ShowBack_toolbar();
        if (myApplication.GetLanguage().equals("en")) {

            ((MainActivity) getActivity()).Change_Tittle(sahtakBilDeniaObObject.getProgramName());
            Name.setText(sahtakBilDeniaObObject.getProgramName());
            String html = sahtakBilDeniaObObject.getDescription();
            wv.loadData(html, "text/html", "utf-8");


        } else {
            ((MainActivity) getActivity()).Change_Tittle(sahtakBilDeniaObObject.getProgramNameAr());
            Name.setText(sahtakBilDeniaObObject.getProgramNameAr());
            String html = sahtakBilDeniaObObject.getDescriptionAr();
            wv.loadData(html, "text/html", "utf-8");

        }


        return view;


    }


    public boolean isFileExists(String Name) {
        File sdCard = Environment.getExternalStorageDirectory();
        String filePath = sdCard.getAbsolutePath() + Name;
        File pdfFile = new File(filePath);
        if (pdfFile.exists()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }


    public class DownloadFileTask {
        public static final String TAG = "DownloadFileTask";

        private Activity context;
        private DownloadFileTask.GetTask contentTask;
        private String url;
        private String fileName;

        public DownloadFileTask(Activity context, String url, String fileName) {
            this.context = context;
            this.url = url;
            this.fileName = fileName;
        }

        public void startTask() {
            doRequest();
        }

        private void doRequest() {
            contentTask = new DownloadFileTask.GetTask();
            contentTask.execute();
        }

        private class GetTask extends AsyncTask<String, Integer, String> {

            @Override
            protected String doInBackground(String... arg0) {
                int count;
                try {
                    Log.d(TAG, "url = " + url);
                    URL _url = new URL(url);
                    URLConnection conection = _url.openConnection();
                    conection.connect();
                    InputStream input = new BufferedInputStream(_url.openStream(),
                            8192);
                    OutputStream output = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + fileName);
                    byte data[] = new byte[1024];
                    while ((count = input.read(data)) != -1) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();
                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                }
                return null;
            }

            protected void onPostExecute(String data) {
                Progress.dismiss();
                EasyToast.success(getActivity(), getString(R.string.success));
                openPdf(Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/download/" + sahtakBilDeniaObObject.getProgramName().replace(" ", "") + ".pdf");

            }
        }
    }

    @Override
    public void onDestroyView() {

        ((MainActivity) getActivity()).HideDownlaod_toolbar();
        super.onDestroyView();
    }

    public void openPdf(String url) {
        File file = new File(url);
        Uri path = Uri.fromFile(file);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);


        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("Error11: ", e.getMessage());
            EasyToast.error(getActivity(), getString(R.string.no_pdf_found));
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://search?q=" + "PDF"));
            startActivity(intent);
        } catch (Exception otherException) {
            Log.e("Error22: ", otherException.getMessage());

            EasyToast.error(getActivity(), getString(R.string.something_went_wrong));
        }
    }

}
