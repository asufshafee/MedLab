package com.mcc.medlabs.view.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.mcc.medlabs.view.Session.MedlabsConstants;
import com.medialablk.easytoast.EasyToast;
import com.mcc.medlabs.view.Activities.MainActivity;
import com.mcc.medlabs.view.R;
import com.mcc.medlabs.view.Session.MyApplication;
import com.mcc.medlabs.view.Utils.AppUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Test_Result_Screen_3 extends Fragment {


    PDFView pdfView;
    Dialog Progress;
    TextView FeedBack, Date;
    String pdfURL = "";
    MyApplication myApplication;

    public Test_Result_Screen_3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test__result__screen_3, container, false);

        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (myApplication.GetLanguage().equals("en"))
            ((MainActivity) getActivity()).Change_Tittle("Test Results");

        else {
            ((MainActivity) getActivity()).Change_Tittle(getString(R.string.Test_Results));

        }
        ((MainActivity) getActivity()).ShowShare_toolbar();


        ((MainActivity) getActivity()).findViewById(R.id.Share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pdfURL.equals("")) {
                    shareViaEmail();
                } else {
                    EasyToast.custom(getActivity(), getString(R.string.message_for_no_link));
                }
            }
        });

        pdfView = (PDFView) view.findViewById(R.id.pdfView);
        FeedBack = (TextView) view.findViewById(R.id.FeedBack);
        Date = (TextView) view.findViewById(R.id.Date);
        Date.setText(getArguments().getString("Date"));
        FeedBack.setPaintFlags(FeedBack.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Progress = AppUtils.LoadingSpinner(getActivity());
        GetPDG(getArguments().getString("FileNo"), getArguments().getString("VisitedID"));

        view.findViewById(R.id.FeedBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Class fragmentClass = null;

                fragmentClass = Feedback_1.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment, "Feedback").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

            }
        });

        return view;


    }

    private void GetPDG(String FileNo, String VisitedID) {

        Progress.show();
        String Url = "http://213.186.160.67:8086/MedlabsApp/GetPdfResultAndroid.aspx?FileNo=" + FileNo + "&visitid=" + VisitedID;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("PDF", response);

                try {
                    mURL = response;
                    pdfURL = response;

                    if (getActivity() != null) {
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    11);
                        } else {
                            downloadFile();
                        }

                    }


                    pdfView.fromUri(Uri.parse("http://213.186.160.67:8086/MedlabsApp/PdfFiles/20154030411ASL1585687.pdf")).onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {

                            Progress.dismiss();
                        }
                    });
                } catch (Exception Ex) {

                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Progress.dismiss();
                        EasyToast.error(getActivity(), getString(R.string.message_for_server_error));
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ServerError) {
                        } else if (error instanceof NetworkError) {
                        } else if (error instanceof ParseError) {
                        }
                    }
                }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }


        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return CubeAnimation.create(CubeAnimation.RIGHT, enter, 500);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 11:
                downloadFile();
                break;
        }
    }

    String mURL = "";

    private void downloadFile() {

        File Directory = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/download/");
        Directory.mkdirs();

        Progress.show();
        DownloadFileTask task = new DownloadFileTask(
                getActivity(),
                mURL,
                "/download/pdf_file.pdf");
        task.startTask();
    }


    public class DownloadFileTask {
        public static final String TAG = "DownloadFileTask";

        private Activity context;
        private GetTask contentTask;
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
            contentTask = new GetTask();
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

                File file = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/download/pdf_file.pdf");
                if (file.exists()) {
                    pdfView.fromFile(file)
                            //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                            .enableSwipe(true)
                            .swipeHorizontal(true)
                            .enableDoubletap(true)
                            .defaultPage(0)
                            .enableAnnotationRendering(true)
                            .password(null)
                            .scrollHandle(null)

                            .onLoad(new OnLoadCompleteListener() {
                                @Override
                                public void loadComplete(int nbPages) {
                                    Progress.dismiss();
                                }
                            })
                            .load();

                }
            }
        }
    }

    @Override
    public void onDestroy() {
        ((MainActivity) getActivity()).HideShare_toolbar();
        super.onDestroy();
    }

    public void shareViaEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.TEST_RESULTS_EMAIL_SUBJECT));
        i.putExtra(Intent.EXTRA_TEXT, MedlabsConstants.EN_EMAIL_BODY + pdfURL);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            EasyToast.error(getActivity(), "There are no email clients installed.");
        }
    }

}
