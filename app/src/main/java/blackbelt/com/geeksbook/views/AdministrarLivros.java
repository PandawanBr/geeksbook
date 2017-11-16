package blackbelt.com.geeksbook.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.dao.LivrariaDao;
import blackbelt.com.geeksbook.utils.Livro;

public class AdministrarLivros extends AppCompatActivity {

    private ListView listaLivros;
    private EditText opcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_livros);
        getSupportActionBar().hide();

        listaLivros = (ListView) findViewById(R.id.admListaLivros);
        opcao = (EditText) findViewById(R.id.txtPesquisarLivros);

        registerForContextMenu(listaLivros);

        listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Livro livro = (Livro) listaLivros.getItemAtPosition(position);

                Intent intent = new Intent(AdministrarLivros.this, CadastroLivro.class);

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

    public void sair(View v){
        finish();
    }

    public void novoLivro(View v){
        Intent intent = new Intent(this, CadastroLivro.class);

        startActivity(intent);
    }

    private void carregarLista(){
        LivrariaDao dao = new LivrariaDao(this);
        List<Livro> livros = dao.listarLivros();
        dao.close();

        ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(
                this, android.R.layout.simple_list_item_1, livros);

        listaLivros.setAdapter(adapter);
    }

    public void pesquisarLivros(View v){
        LivrariaDao dao = new LivrariaDao(this);
        List<Livro> livros = dao.listarGeral(opcao.getText().toString());
        dao.close();

        if (!opcao.getText().toString().isEmpty()){

            ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(
                    this, android.R.layout.simple_list_item_1, livros);

            listaLivros.setAdapter(adapter);
        } else {
            carregarLista();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) menuInfo;

                Livro livro = (Livro) listaLivros.getItemAtPosition(info.position);

                LivrariaDao dao = new LivrariaDao(AdministrarLivros.this);
                dao.deleta(livro);
                dao.close();

                carregarLista();

                return false;
            }
        });

    }


}
