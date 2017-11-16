package blackbelt.com.geeksbook.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.dao.LivrariaDao;
import blackbelt.com.geeksbook.utils.Livro;

public class CatalogoBusca extends AppCompatActivity {

    private ListView listarLivros;
    private EditText pesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_busca);
        getSupportActionBar().hide();

        listarLivros = (ListView) findViewById(R.id.ListaBusca);
        pesquisa = (EditText) findViewById(R.id.txtLivroBusca);

    }

    public void voltar(View v){
        finish();
    }

    @Override
    public void onResume(){
        super.onResume();

        carregarLista();
    }

    private void carregarLista(){
        LivrariaDao dao = new LivrariaDao(this);
        List<Livro> livros = dao.listarLivros();
        dao.close();

        ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(
                this, android.R.layout.simple_list_item_1, livros);

        listarLivros.setAdapter(adapter);

    }

    public void buscarLivros(View v){
        LivrariaDao dao = new LivrariaDao(this);
        String pesquisar = pesquisa.getText().toString();

        if(!pesquisar.isEmpty()){
            List<Livro> livros = dao.pesquisaLivro(pesquisar);
            dao.close();

            ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(
                    this, android.R.layout.simple_list_item_1, livros
            );

            listarLivros.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Preencha o campo!", Toast.LENGTH_SHORT).show();
            carregarLista();
        }


    }
}
