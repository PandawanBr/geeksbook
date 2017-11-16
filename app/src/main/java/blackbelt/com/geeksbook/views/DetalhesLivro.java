package blackbelt.com.geeksbook.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.utils.Livro;

public class DetalhesLivro extends AppCompatActivity {

    private ImageView imagemCapa;
    private TextView titulo, subtitulo, autor, isbn, editora,
        edicao, anopublic, numpag, cate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_livro);

        titulo = (TextView) findViewById(R.id.titulo);
        subtitulo = (TextView) findViewById(R.id.subtitulo);
        autor = (TextView) findViewById(R.id.autor);
        isbn = (TextView) findViewById(R.id.isbn);
        editora = (TextView) findViewById(R.id.editora);
        edicao = (TextView) findViewById(R.id.edicao);
        anopublic = (TextView) findViewById(R.id.anopub);
        numpag = (TextView) findViewById(R.id.numpag);
        cate = (TextView) findViewById(R.id.categoria);
        imagemCapa = (ImageView) findViewById(R.id.imagemCapa);

        Intent intent = getIntent();
        Livro livro = (Livro) intent.getSerializableExtra("livro");

        imagemCapa.setImageURI(Uri.parse(livro.getImagemCapa()));
        isbn.setText("ISBN: " + String.valueOf(livro.getIsbn()));
        titulo.setText(livro.getTitulo());
        subtitulo.setText(livro.getSubTitulo());
        edicao.setText("Edição: " + String.valueOf(livro.getEdicao()));
        autor.setText(livro.getAutor());
        numpag.setText("Quant. de Págs: " + String.valueOf(livro.getQtdPags()) + "p");
        anopublic.setText("Ano de Publicação: " + String.valueOf(livro.getAnoPub()));
        editora.setText("Editora: " + livro.getNomeEditora());
        cate.setText(livro.getCategoria());
    }

    public void voltar(View v){
        finish();
    }
}
