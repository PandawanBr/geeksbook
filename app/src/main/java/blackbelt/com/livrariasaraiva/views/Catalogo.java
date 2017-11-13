package blackbelt.com.livrariasaraiva.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import blackbelt.com.livrariasaraiva.R;

public class Catalogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        getSupportActionBar().hide();
    }

    public void catalogoTodosLivros(View v){
        Intent intent = new Intent(this, CatalogoTodosLivros.class);

        startActivity(intent);
    }

    public void catalogoCategoria(View v){
        Intent intent = new Intent(this, CatalogoCategoria.class);

        startActivity(intent);
    }

    public void catalogoBusca(View v){
        Intent intent = new Intent(this, CatalogoBusca.class);

        startActivity(intent);
    }

    public void voltar(View v){
        finish();
    }
}
