package blackbelt.com.geeksbook.views;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.dao.LivrariaDao;
import blackbelt.com.geeksbook.utils.Login;

public class TelaLogin extends AppCompatActivity {

    private Button esqueceuSenha;
    private Button primeiroAcesso;
    private ImageButton logar;
    private ImageButton sair;
    private EditText login, senha;

    Login loginCad = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        getSupportActionBar().hide();

        logar = (ImageButton) findViewById(R.id.entrarLogin);
        sair = (ImageButton) findViewById(R.id.sairLogin);
        esqueceuSenha = (Button) findViewById(R.id.esqueceuSenha);
        primeiroAcesso = (Button) findViewById(R.id.primeiroLogin);
        login = (EditText) findViewById(R.id.loginPrincipal);
        senha = (EditText) findViewById(R.id.senhaPincipal);

        esqueceuSenha.setPaintFlags(esqueceuSenha.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        primeiroAcesso.setPaintFlags(primeiroAcesso.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


    }

    public void logarMenu(View botao) {
        LivrariaDao dao = new LivrariaDao(this);
        String loginValidar = login.getText().toString();
        String senhaValidar = senha.getText().toString();

        if(!loginValidar.isEmpty() && !senhaValidar.isEmpty()){
            if (dao.realizarLogin(loginValidar, senhaValidar)){

                loginCad.setLogin(loginValidar.toString());
                loginCad.setSenha(senhaValidar.toString());

                Toast.makeText(this, "Acessando", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MenuPrincipal.class);

                intent.putExtra("login", loginCad);

                startActivity(intent);

            } else {
                Toast.makeText(this, "Login ou Senha incorretos", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
        }

    }

    public void sair(View botao) {
        finish();
    }

    public void primeiroLogin(View botao) {
        Intent intent = new Intent(this, PrimeiroAcesso.class);
        startActivity(intent);
    }

    public void esqueceuASenha(View v) {
        Intent intent = new Intent(this, EsqueceuSenha.class);
        startActivity(intent);
    }


}
