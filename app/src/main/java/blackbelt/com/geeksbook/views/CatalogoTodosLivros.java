package blackbelt.com.geeksbook.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.adapter.LivrosAdapter;
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

        listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Livro livro = (Livro) listaLivros.getItemAtPosition(position);

                Intent intent = new Intent(CatalogoTodosLivros.this, DetalhesLivro.class);

                intent.putExtra("livro", livro);

                startActivity(intent);

            }
        });



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

        LivrosAdapter adapter = new LivrosAdapter(this, livros);

        listaLivros.setAdapter(adapter);

    }
}
