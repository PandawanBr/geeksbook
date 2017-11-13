package blackbelt.com.livrariasaraiva.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import blackbelt.com.livrariasaraiva.R;

public class EquipeDesenvolvimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe_desenvolvimento);
        getSupportActionBar().hide();
    }

    public void voltar(View v){
        finish();
    }


}
