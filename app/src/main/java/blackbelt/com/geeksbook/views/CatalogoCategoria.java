package blackbelt.com.geeksbook.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.dao.LivrariaDao;
import blackbelt.com.geeksbook.utils.Livro;

public class CatalogoCategoria extends AppCompatActivity {

    private ListView listaCatalogo;
    private Spinner spinner;
    private String opcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_categoria);
        getSupportActionBar().hide();

        spinner = (Spinner) findViewById(R.id.selectCategoria);
        listaCatalogo = (ListView) findViewById(R.id.catalogoLivrosCategoria);

        String[] categorias = getResources().getStringArray(R.array.categorias);

        opcao = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LivrariaDao dao = new LivrariaDao(CatalogoCategoria.this);
                opcao = spinner.getSelectedItem().toString();

                List<Livro> livros = dao.listarLivrosCategoria(opcao);
                dao.close();

                ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(
                        CatalogoCategoria.this, android.R.layout.simple_list_item_1, livros);

                listaCatalogo.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void voltar(View v){
        finish();
    }
}
