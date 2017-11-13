package blackbelt.com.livrariasaraiva.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import blackbelt.com.livrariasaraiva.R;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        getSupportActionBar().hide();

    }

    public void sairMenu(View botao) {
        finish();
    }


    public void catalogo(View v){
        Intent intent = new Intent(this, Catalogo.class);

        startActivity(intent);

    }

    public void administarDados(View v){
        Intent intent = new Intent(this, AdministrarLivros.class);

        startActivity(intent);
    }

    public void alterarSenha(View v){
        Intent intent = new Intent(this, AlteracaoDeSenha.class);

        startActivity(intent);
    }

    public void equipeDesenvolvimento(View v){
        Intent intent = new Intent(this, EquipeDesenvolvimento.class);

        startActivity(intent);
    }

}
