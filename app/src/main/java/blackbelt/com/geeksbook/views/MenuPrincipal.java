package blackbelt.com.geeksbook.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.utils.Login;

public class MenuPrincipal extends AppCompatActivity {

    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        login = (Login) intent.getSerializableExtra("login");

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

        intent.putExtra("login", login);

        startActivity(intent);
    }

    public void equipeDesenvolvimento(View v){
        Intent intent = new Intent(this, EquipeDesenvolvimento.class);

        startActivity(intent);
    }

}
