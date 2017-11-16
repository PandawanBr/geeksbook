package blackbelt.com.geeksbook.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.dao.LivrariaDao;

public class EsqueceuSenha extends AppCompatActivity {

    private EditText senhaNova, senhaConfirma, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        login = (EditText) findViewById(R.id.loginNovo);
        senhaNova = (EditText) findViewById(R.id.novaSenha);
        senhaConfirma = (EditText) findViewById(R.id.confirmaSenha);

    }

    public void sair(View v) {
        finish();
    }

    public void gerarNovaSenha(View v){
        String loginUsuario = login.getText().toString();
        String senhaAtt = senhaNova.getText().toString();
        String senhaConf = senhaConfirma.getText().toString();

        LivrariaDao dao = new LivrariaDao(this);

        if(dao.verificarLogin(loginUsuario)){
            if (senhaAtt.equals(senhaConf)) {
                dao.atualizarSenha(loginUsuario, senhaConf);

                Toast.makeText(this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();

                finish();

            } else {
                Toast.makeText(this, "Senhas nao conferem!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Usuário Inválido!", Toast.LENGTH_SHORT).show();
        }
    }}
