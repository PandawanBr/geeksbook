package blackbelt.com.geeksbook.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import blackbelt.com.geeksbook.utils.Livro;
import blackbelt.com.geeksbook.utils.Login;

/**
 * Created by kobayashi on 05/11/17.
 */

public class LivrariaDao extends SQLiteOpenHelper{

    public LivrariaDao(Context context){
        super(context, "tb_livros", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_livros (" +
                "nr_id INTEGER PRIMARY KEY, " +
                "nm_titulo TEXT NOT NULL," +
                "cd_isbn NUMBER NOT NULL," +
                "nm_subtitulo TEXT," +
                "nr_edicao NUMBER NOT NULL," +
                "nm_autor TEXT NOT NULL," +
                "qt_qtpags NUMBER NOT NULL," +
                "dt_anopub NUMBER NOT NULL," +
                "nm_editora TEXT NOT NULL," +
                "nr_categoria TEXT NOT NULL," +
                "img_imagem TEXT)";

        String sql1 = "CREATE TABLE tb_acesso (" +
                "nm_login TEXT PRIMARY KEY, " +
                "txt_senha TEXT NOT NULL)";

        db.execSQL(sql);
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tb_livros";
        String sql1 = "DROP TABLE IF EXISTS tb_acesso";

        db.execSQL(sql);
        onCreate(db);

        db.execSQL(sql1);
        onCreate(db);

    }

    public void insereLivro(Livro livro) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nm_titulo", livro.getTitulo());
        dados.put("cd_isbn", livro.getIsbn());
        dados.put("nm_subtitulo", livro.getSubTitulo());
        dados.put("nr_edicao", livro.getEdicao());
        dados.put("nm_autor", livro.getAutor());
        dados.put("qt_qtpags", livro.getQtdPags());
        dados.put("dt_anopub", livro.getAnoPub());
        dados.put("nm_editora", livro.getNomeEditora());
        dados.put("nr_categoria", livro.getCategoria());
        dados.put("img_imagem", livro.getImagemCapa());

        db.insert("tb_livros", null, dados);

    }

    public void insereLogin(Login login){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nm_login", login.getLogin());
        dados.put("txt_senha", login.getSenha());

        db.insert("tb_acesso", null, dados);
    }

    public boolean realizarLogin(String login, String senha) {
        String[] logar = new String[]{login, senha};
        Cursor db = getReadableDatabase().query("tb_acesso", null,
                "nm_login = ? AND txt_senha = ?",
                logar, null, null, null);

        if (db.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<Livro> listarLivros(){
        String sql = "SELECT * FROM tb_livros";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Livro> livros = new ArrayList<>();

        while(c.moveToNext()){
            Livro livro = new Livro();

            livro.setId(c.getLong(c.getColumnIndex("nr_id")));
            livro.setImagemCapa(c.getString(c.getColumnIndex("img_imagem")));
            livro.setIsbn(c.getInt(c.getColumnIndex("cd_isbn")));
            livro.setTitulo(c.getString(c.getColumnIndex("nm_titulo")));
            livro.setSubTitulo(c.getString(c.getColumnIndex("nm_subtitulo")));
            livro.setEdicao(c.getInt(c.getColumnIndex("nr_edicao")));
            livro.setAutor(c.getString(c.getColumnIndex("nm_autor")));
            livro.setQtdPags(c.getInt(c.getColumnIndex("qt_qtpags")));
            livro.setAnoPub(c.getInt(c.getColumnIndex("dt_anopub")));
            livro.setNomeEditora(c.getString(c.getColumnIndex("nm_editora")));
            livro.setCategoria(c.getString(c.getColumnIndex("nr_categoria")));

            livros.add(livro);

        }
        c.close();

        return livros;
    }

    public void deleta(Livro livro) {
        SQLiteDatabase db = getWritableDatabase();


        String[] params = {String.valueOf(livro.getId()).toString()};

        db.delete("tb_livros", "nr_id = ?", params);

    }

    public List<Livro> pesquisaLivro(String titulo){
        List<Livro> listaLivros = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tb_livros WHERE nm_autor = '" + titulo + "' OR nm_titulo = '" + titulo +"'";

        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()) {
            Livro livro = new Livro();

            livro.setId(c.getLong(c.getColumnIndex("nr_id")));
            livro.setImagemCapa(c.getString(c.getColumnIndex("img_imagem")));
            livro.setIsbn(c.getInt(c.getColumnIndex("cd_isbn")));
            livro.setTitulo(c.getString(c.getColumnIndex("nm_titulo")));
            livro.setSubTitulo(c.getString(c.getColumnIndex("nm_subtitulo")));
            livro.setEdicao(c.getInt(c.getColumnIndex("nr_edicao")));
            livro.setAutor(c.getString(c.getColumnIndex("nm_autor")));
            livro.setQtdPags(c.getInt(c.getColumnIndex("qt_qtpags")));
            livro.setAnoPub(c.getInt(c.getColumnIndex("dt_anopub")));
            livro.setNomeEditora(c.getString(c.getColumnIndex("nm_editora")));
            livro.setCategoria(c.getString(c.getColumnIndex("nr_categoria")));

            listaLivros.add(livro);

        }
        c.close();

        return listaLivros;
    }

    public List<Livro> listarGeral(String opcao){
        List<Livro> listaLivros = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tb_livros WHERE " +
                "nr_categoria = '" + opcao + "' " +
                "OR nm_autor = '" + opcao + "' " +
                "OR nm_titulo = '" + opcao + "'";

        Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()){
            Livro livro = new Livro();

            livro.setId(c.getLong(c.getColumnIndex("nr_id")));
            livro.setImagemCapa(c.getString(c.getColumnIndex("img_imagem")));
            livro.setIsbn(c.getInt(c.getColumnIndex("cd_isbn")));
            livro.setTitulo(c.getString(c.getColumnIndex("nm_titulo")));
            livro.setSubTitulo(c.getString(c.getColumnIndex("nm_subtitulo")));
            livro.setEdicao(c.getInt(c.getColumnIndex("nr_edicao")));
            livro.setAutor(c.getString(c.getColumnIndex("nm_autor")));
            livro.setQtdPags(c.getInt(c.getColumnIndex("qt_qtpags")));
            livro.setAnoPub(c.getInt(c.getColumnIndex("dt_anopub")));
            livro.setNomeEditora(c.getString(c.getColumnIndex("nm_editora")));
            livro.setCategoria(c.getString(c.getColumnIndex("nr_categoria")));

            listaLivros.add(livro);

        }
        c.close();

        return listaLivros;
    }

    public List<Livro> listarLivrosCategoria(String opcao){

        List<Livro> listaLivros = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tb_livros WHERE nr_categoria = '" + opcao + "'";

        Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()){
            Livro livro = new Livro();

            livro.setId(c.getLong(c.getColumnIndex("nr_id")));
            livro.setImagemCapa(c.getString(c.getColumnIndex("img_imagem")));
            livro.setIsbn(c.getInt(c.getColumnIndex("cd_isbn")));
            livro.setTitulo(c.getString(c.getColumnIndex("nm_titulo")));
            livro.setSubTitulo(c.getString(c.getColumnIndex("nm_subtitulo")));
            livro.setEdicao(c.getInt(c.getColumnIndex("nr_edicao")));
            livro.setAutor(c.getString(c.getColumnIndex("nm_autor")));
            livro.setQtdPags(c.getInt(c.getColumnIndex("qt_qtpags")));
            livro.setAnoPub(c.getInt(c.getColumnIndex("dt_anopub")));
            livro.setNomeEditora(c.getString(c.getColumnIndex("nm_editora")));
            livro.setCategoria(c.getString(c.getColumnIndex("nr_categoria")));

            listaLivros.add(livro);

        }
        c.close();

        return listaLivros;
    }

    public void alterar(Livro livro) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosLivros(livro);

        String[] params = {String.valueOf(livro.getId()).toString()};

        db.update("tb_livros", dados, "nr_id = ?", params);

    }

    private ContentValues pegaDadosLivros(Livro livro) {
        ContentValues dados = new ContentValues();

        dados.put("nm_titulo", livro.getTitulo());
        dados.put("cd_isbn", livro.getIsbn());
        dados.put("nm_subtitulo", livro.getSubTitulo());
        dados.put("nr_edicao", livro.getEdicao());
        dados.put("nm_autor", livro.getAutor());
        dados.put("qt_qtpags", livro.getQtdPags());
        dados.put("dt_anopub", livro.getAnoPub());
        dados.put("nm_editora", livro.getNomeEditora());
        dados.put("nr_categoria", livro.getCategoria());
        dados.put("img_imagem", livro.getImagemCapa());

        return dados;
    }

    public void atualizarSenha(String log, String senCo) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();

        String[] params = {log};

        dados.put("txt_senha", senCo);

        db.update("tb_acesso", dados, "nm_login = ?", params);
    }
}
