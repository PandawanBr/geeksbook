package blackbelt.com.livrariasaraiva.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import blackbelt.com.livrariasaraiva.R;
import blackbelt.com.livrariasaraiva.dao.LivrariaDao;
import blackbelt.com.livrariasaraiva.utils.Livro;

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
        MenuItem editar = menu.add("Editar");
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
