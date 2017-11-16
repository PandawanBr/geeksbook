package blackbelt.com.geeksbook.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.dao.LivrariaDao;
import blackbelt.com.geeksbook.helper.FormularioHelper;
import blackbelt.com.geeksbook.utils.Livro;

public class AlterarLivro extends AppCompatActivity {

    private ImageView imagemCapa;
    private EditText isbn;
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_livro);
        getSupportActionBar().hide();

        isbn = (EditText) findViewById(R.id.cadastroIsbn);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Livro livro = (Livro) intent.getSerializableExtra("livro");


        helper.preencheFormulario(livro);

        isbn.setEnabled(false);

    }

    public void atualizarDados(View view){

        LivrariaDao dao = new LivrariaDao(this);

        Livro livro = helper.pegaLivro();


        dao.alterar(livro);

        dao.close();

        Toast.makeText(this, "Atualizado com sucesso!!", Toast.LENGTH_SHORT).show();

        finish();

    }

    public void voltar (View v){
        finish();
    }
}
