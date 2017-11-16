package blackbelt.com.geeksbook.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.dao.LivrariaDao;
import blackbelt.com.geeksbook.utils.Livro;

public class CatalogoTodosLivros extends AppCompatActivity {

    private ListView listaLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_todos_livros);
        getSupportActionBar().hide();

        listaLivros = (ListView) findViewById(R.id.catalogoTodosLivros);



    }

    @Override
    public void onResume(){
        super.onResume();

        carregarLista();
    }

    public void voltar(View v){
        finish();
    }

    private void carregarLista(){
        LivrariaDao dao = new LivrariaDao(this);
        List<Livro> livros = dao.listarLivros();
        dao.close();

        ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(
                this, android.R.layout.simple_list_item_1, livros);

        listaLivros.setAdapter(adapter);

    }
}
