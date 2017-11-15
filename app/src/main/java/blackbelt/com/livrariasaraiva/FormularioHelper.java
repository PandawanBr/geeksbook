package blackbelt.com.livrariasaraiva;

import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import blackbelt.com.livrariasaraiva.utils.Livro;
import blackbelt.com.livrariasaraiva.views.CadastroLivro;

/**
 * Created by kobayashi on 15/11/17.
 */

public class FormularioHelper {

    private final ImageView imagemCapa;
    private final EditText isbn;
    private final EditText titulo;
    private final EditText subTitulo;
    private final EditText edicao;
    private final EditText autor;
    private final EditText qtdPaginas;
    private final EditText anoPublicacao;
    private final EditText editora;
    private final Spinner categoria;
    private Uri caminhoImagem;

    private Livro livro;

    public Uri getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(Uri caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public FormularioHelper(CadastroLivro cadastroLivro){
        imagemCapa = (ImageView) cadastroLivro.findViewById(R.id.cadastroImagem);
        isbn = (EditText) cadastroLivro.findViewById(R.id.cadastroIsbn);
        titulo = (EditText) cadastroLivro.findViewById(R.id.cadastroTitulo);
        subTitulo = (EditText) cadastroLivro.findViewById(R.id.cadastroSubTitulo);
        edicao = (EditText) cadastroLivro.findViewById(R.id.cadastroEdicao);
        autor = (EditText) cadastroLivro.findViewById(R.id.cadastroAutor);
        qtdPaginas = (EditText) cadastroLivro.findViewById(R.id.cadastroQtdPag);
        anoPublicacao = (EditText) cadastroLivro.findViewById(R.id.cadastroAnoPub);
        editora = (EditText) cadastroLivro.findViewById(R.id.cadastroNomeEditora);
        categoria = (Spinner) cadastroLivro.findViewById(R.id.cadastroCategoria);

        livro = new Livro();
    }

    public Livro pegaLivro(){

        livro.setImagemCapa(this.caminhoImagem.toString());
        livro.setIsbn(Integer.parseInt(isbn.getText().toString()));
        livro.setTitulo(titulo.getText().toString());
        livro.setSubTitulo(subTitulo.getText().toString());
        livro.setEdicao(Integer.parseInt(edicao.getText().toString()));
        livro.setAutor(autor.getText().toString());
        livro.setQtdPags(Integer.parseInt(qtdPaginas.getText().toString()));
        livro.setAnoPub(Integer.parseInt(anoPublicacao.getText().toString()));
        livro.setNomeEditora(editora.getText().toString());
        livro.setCategoria(categoria.getSelectedItem().toString());

        return livro;
    }

    public boolean conferirCampos(){
        if(!isbn.getText().equals("") &&
                !titulo.getText().equals("") &&
                !edicao.getText().equals("") &&
                !autor.getText().equals("") &&
                !qtdPaginas.getText().equals("") &&
                !anoPublicacao.getText().equals("") &&
                !editora.getText().equals("") &&
                !categoria.getSelectedItem().equals("Selecione uma categoria...")) {

            return true;

        } else {

            return false;

        }
    }


    public void preencheFormulario(Livro livro) {
        imagemCapa.setImageURI(Uri.parse(livro.getImagemCapa()));
        isbn.setText(String.valueOf(livro.getIsbn()));
        titulo.setText(livro.getTitulo());
        subTitulo.setText(livro.getSubTitulo());
        edicao.setText(String.valueOf(livro.getEdicao()));
        autor.setText(livro.getAutor());
        qtdPaginas.setText(String.valueOf(livro.getQtdPags()));
        anoPublicacao.setText(String.valueOf(livro.getAnoPub()));
        editora.setText(livro.getNomeEditora());



        this.livro = livro;

    }
}
