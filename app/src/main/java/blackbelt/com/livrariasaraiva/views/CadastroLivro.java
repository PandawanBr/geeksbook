package blackbelt.com.livrariasaraiva.views;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import blackbelt.com.livrariasaraiva.FormularioHelper;
import blackbelt.com.livrariasaraiva.R;
import blackbelt.com.livrariasaraiva.dao.LivrariaDao;
import blackbelt.com.livrariasaraiva.utils.Livro;

public class CadastroLivro extends AppCompatActivity {

    private ImageView imagemCapa;
    private EditText isbn;
    private FormularioHelper helper;
    public static final int PICK_IMAGE = 1234;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);
        getSupportActionBar().hide();

        isbn = (EditText) findViewById(R.id.cadastroIsbn);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Livro livro = (Livro) intent.getSerializableExtra("livro");

        if (livro != null){
            helper.preencheFormulario(livro);

            isbn.setEnabled(false);
        }

    }

    public void selecionarImagem(View v){
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imagemCapa = (ImageView) findViewById(R.id.cadastroImagem);
            imagemCapa.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            Toast.makeText(this, picturePath, Toast.LENGTH_SHORT).show();
            helper.setCaminhoImagem(selectedImage);
        }
    }

    public void voltar (View v){
        finish();
    }

    public void salvarBanco(View view){

        LivrariaDao dao = new LivrariaDao(this);


        Livro livro = new Livro();

        if(helper.conferirCampos()) {

            dao.insereLivro(helper.pegaLivro());
            dao.close();

            Toast.makeText(this, "Salvo com sucesso!!", Toast.LENGTH_SHORT).show();

            finish();

        } else {
            Toast.makeText(this, "Preencha todos os campos!!", Toast.LENGTH_SHORT).show();
        }
    }

}
