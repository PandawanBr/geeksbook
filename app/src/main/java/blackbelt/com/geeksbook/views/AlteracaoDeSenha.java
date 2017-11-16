package blackbelt.com.geeksbook.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.dao.LivrariaDao;
import blackbelt.com.geeksbook.utils.Login;

public class AlteracaoDeSenha extends AppCompatActivity {

    private Login login;
    private String logAtual, senAtiva;
    private EditText senhaAtual, novaSenha, confSenha, loginAtivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao_de_senha);
        getSupportActionBar().hide();

        loginAtivo = (EditText) findViewById(R.id.login);
        senhaAtual = (EditText) findViewById(R.id.senhaAtual);
        novaSenha = (EditText) findViewById(R.id.novaSenha);
        confSenha = (EditText) findViewById(R.id.confirmaSenha);

        Intent intent = getIntent();

        login = (Login) intent.getSerializableExtra("login");

        loginAtivo.setText(login.getLogin());

    }

    public void voltar(View v){
        finish();
    }

    public void atualizarSenha(View v) {
        String senAt = senhaAtual.getText().toString();
        String senNo = novaSenha.getText().toString();
        String senCo = confSenha.getText().toString();

        senAtiva = login.getSenha();
        logAtual = login.getLogin();

        LivrariaDao dao = new LivrariaDao(this);

        if(!senAt.isEmpty() && !senNo.isEmpty() && !senCo.isEmpty()){
            if (senAt.equals(senAtiva)){
                if(senNo.equals(senCo)) {
                    dao.atualizarSenha(logAtual, senCo);

                    Toast.makeText(this,
                            "Senha Atualizada com sucesso",
                            Toast.LENGTH_SHORT).show();

                    finish();

                } else {
                    Toast.makeText(this,
                            "Senhas Diferentes", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,
                        "Senha Atual Inválida", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,
                    "Preencha todos os Campos!!", Toast.LENGTH_SHORT).show();
        }


    }
}
