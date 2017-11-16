package blackbelt.com.geeksbook.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import blackbelt.com.geeksbook.utils.Login;
import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.dao.LivrariaDao;

public class PrimeiroAcesso extends AppCompatActivity {

    private EditText login, senha, confSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiro_acesso);
        getSupportActionBar().hide();

        login = (EditText) findViewById(R.id.primeiroLogin);
        senha = (EditText) findViewById(R.id.primeiraSenha);
        confSenha = (EditText) findViewById(R.id.primeiroConfSenha);
    }

    public void salvarLogin(View v){
        String pLogin = login.getText().toString();
        String pSenha = senha.getText().toString();
        String pConfSenha = confSenha.getText().toString();

        Login log = new Login();
        LivrariaDao dao = new LivrariaDao(this);

        log.setLogin(pLogin);

        if(pSenha.equals(pConfSenha)) {
            log.setSenha(pConfSenha);

            dao.insereLogin(log);
            dao.close();

            Toast.makeText(this, "Usuário Salvo com Sucesso",
                    Toast.LENGTH_LONG).show();

            finish();

        } else {
            Toast.makeText(this, "Senhas são diferentes",
                    Toast.LENGTH_LONG).show();

        }

    }

    public void voltar(View v){
        finish();
    }
}
