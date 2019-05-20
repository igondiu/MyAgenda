package com.example.myagenda.databaseClasses;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class ConnectionJson extends AsyncTask<String, Void, List<User>> {

    String server_response;
    String userID;
    public ConnectionJson(String string){
        String server_response = "NULL";
        userID = string;
    }




    List<User> listUser = new ArrayList<>();

    @Override
    protected List<User> doInBackground(String... urlTable) {

        try {
            JSONObject json = new JSONObject();
            json.put("id",userID);
            /*json.put("name","Strawsberry");
            json.put("type","Legume");
            json.put("price",8.6);*/
            listUser = parse(get(urlTable[0],"GET",json));
            for (int i=0;i<listUser.size();i++){
                Log.v("PRODUIT ", listUser.get(i).getId()+" "+listUser.get(i).getNom()+" "+listUser.get(i).getPrenom()+"\n");
            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listUser;
    }

    public String get(String url, String pMethode, JSONObject pdt) throws IOException, JSONException {
        InputStream is = null;
        String parameters  = "r="+URLEncoder.encode(pdt.toString(), "utf-8");
        try {
            if(pMethode.equals("DELETE")){
                url+=pdt.getInt("id");
            }else if(pMethode.equals("GET")){
                url+="?"+parameters;
            }
            Log.v("R ", pdt.toString()+"\n");
            final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod(pMethode);

            // Pour les methode POST et PUT on envoie les parametre avec l'OutputStreamWriter
            if(pMethode.equals("POST")||pMethode.equals("PUT")){
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(parameters);// here i sent the parameter
                out.close();
            }else{
                conn.setDoInput(true);
                conn.connect();
            }

            // d�marre la connexion
            conn.connect();
            /*BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            writer.write("r="+URLEncoder.encode(pdt.toString(),"utf-8"));
            writer.close();*/


            is = conn.getInputStream();

            // Lit le InputStream et le sauve dans une cha�ne
            return readIt(is);
        } finally {
            // S'assure que le InputStream est ferm� apr�s l?arr�t de l'application
            if (is != null) {
                is.close();
            }
        }
    }

    @Override
    protected void onPostExecute(List<User> s) {
        super.onPostExecute(s);
        Log.e("Response", "" + server_response);
    }

    private List<User> parse(final String json) {
        try {
            final List user = new ArrayList();
            final JSONArray jUserArray = new JSONArray(json);
            for (int i = 0; i < jUserArray.length(); i++) {
                user.add(new User(jUserArray.optJSONObject(i)));
            }
            return user;
        } catch (JSONException e) {
            Log.v(ContentValues.TAG, "[JSONException] e : " + e.getMessage());
        }
        return null;
    }

    private String readIt(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            response.append(line).append('\n');
        }
        return response.toString();
    }

    public List<User> getuser(){
        return this.listUser;
    }
}
