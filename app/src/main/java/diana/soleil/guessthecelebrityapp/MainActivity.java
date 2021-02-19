package diana.soleil.guessthecelebrityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button button1,button2,button3,button4;
    ImageView imageView;
    URL url;
    ArrayList<String> arrayName;
    ArrayList<String> arrayLink;
    int random1,random2,random3,randpm4,random5, random6;

    public void startTheGame () {

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        Bitmap image =null;
        DownloadImage downloadImage = new DownloadImage();
        imageView = (ImageView) findViewById(R.id.imageView);
        random2 = randomNumber();
        random3 = randomNumber2();
        randpm4 = randomNumber2();
        random5 = randomNumber2();
        random6 = randomNumber2();
        if (random3 == randpm4) {
            randpm4 = randomNumber2();
            if (randpm4 == random5) {
                random5 = randomNumber2();
                if (random5 == random6) {
                    random6 = randomNumber2();
                }
            }
        }
        if (random3 == random5) {
            random5 = randomNumber2();
            if (randpm4 == random5) {
                randpm4 = randomNumber2();
                if (randpm4 == random6) {
                    random6 = randomNumber2();
                }
            }
        }
        if (random3 == random6) {
            random6 = randomNumber2();
            if (randpm4 == random6) {
                randpm4 = randomNumber2();
                if (randpm4 == random5) {
                    random5 = randomNumber2();
                }
            }
        }







        switch (random2) {
            case 1:
                if (random2 == Integer.parseInt(button1.getTag().toString())) {
                    button1.setText(arrayName.get(random3));
                    try {
                        image = downloadImage.execute(arrayLink.get(random3).toString()).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(image);

                    button2.setText(arrayName.get(randpm4));
                    button3.setText(arrayName.get(random5));
                    button4.setText(arrayName.get(random6));

                }
                break;

            case 2:
                if (random2 == Integer.parseInt(button2.getTag().toString())) {
                    button2.setText(arrayName.get(random3));
                    try {
                        image = downloadImage.execute(arrayLink.get(random3).toString()).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(image);

                    button1.setText(arrayName.get(randpm4));
                    button3.setText(arrayName.get(random5));
                    button4.setText(arrayName.get(random6));

                }
                break;
            case 3:
                if (random2 == Integer.parseInt(button3.getTag().toString())) {
                    button3.setText(arrayName.get(random3));
                    try {
                        image = downloadImage.execute(arrayLink.get(random3).toString()).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(image);

                    button1.setText(arrayName.get(randpm4));
                    button2.setText(arrayName.get(random5));
                    button4.setText(arrayName.get(random6));

                }
                break;
            case 4:
                if (random2 == Integer.parseInt(button4.getTag().toString())) {
                    button4.setText(arrayName.get(random3));
                    try {
                        image = downloadImage.execute(arrayLink.get(random3).toString()).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(image);

                    button2.setText(arrayName.get(random5));
                    button3.setText(arrayName.get(randpm4));
                    button1.setText(arrayName.get(random6));

                }
                break;
        }
    }
    public void goToNext(View view) {
        Button view1 = (Button) view;

        if (Integer.parseInt(view1.getTag().toString()) == random2) {
            Toast.makeText(this, "You answer right!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "You Suck", Toast.LENGTH_SHORT).show();
        }


        Log.i("random1",String.valueOf(random1));
        Log.i("random2",String.valueOf(random2));
        Log.i("random3",String.valueOf(random3));
        Log.i("random4",String.valueOf(randpm4));
        Log.i("random5",String.valueOf(random5));
        Log.i("random6",String.valueOf(random6));
        Log.i("Tag",view1.getTag().toString());
        startTheGame();

    }

    public class DownloadText extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String result="";
            HttpsURLConnection  httpURLConnection = null;
            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpsURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int read = reader.read();
                while (read != -1) {
                    char current = (char) read;
                    result += current;
                    read = reader.read();
                }
                return result;


            } catch (MalformedURLException e) {

                e.printStackTrace();
            return  "Failed";
            } catch (IOException e) {
                e.printStackTrace();
            return  "Failed";
            }

        }
    }
    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... arrayLists) {
            try {

                URL urlImage = new URL(arrayLists[0]);
                HttpsURLConnection httpsURLConnectionImagel = (HttpsURLConnection) urlImage.openConnection();
                httpsURLConnectionImagel.connect();
                InputStream in = httpsURLConnectionImagel.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    public int randomNumber(){
        Random random = new Random();
        random1 = random.nextInt(4)+1;
        return random1;
    }
    public int randomNumber2(){
        Random random = new Random();
        random1 = random.nextInt(49)+1;
        return random1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadText downloadText = new DownloadText();


        arrayName = new ArrayList<String>();
        arrayLink = new ArrayList<String>();
        String result2  = null;

        try {

            result2 = downloadText.execute("https://www.lovefood.com/gallerylist/81561/the-worlds-50-most-delicious-dishes-youll-want-to-try").get();

        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.i("Error", e.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.i("Error", e.toString());
        }
        Pattern p = Pattern.compile("\"gallery__caption-title u-mb-\">(.*?)</h2>");
        Matcher matchername = p.matcher(result2);
        while (matchername.find()) {
            arrayName.add ( matchername.group(1));
        }
        Pattern p2 = Pattern.compile("<img src=\"(.*?)\"/>");
        Matcher matchername2 = p2.matcher(result2);
        while (matchername2.find()) {
            arrayLink.add ( matchername2.group(1));
        }


        startTheGame();



    }
}