package blackbelt.com.geeksbook.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import blackbelt.com.geeksbook.R;

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
