package blackbelt.com.geeksbook.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import blackbelt.com.geeksbook.R;
import blackbelt.com.geeksbook.utils.Livro;

/**
 * Created by kobayashi on 15/11/17.
 */

public class LivrosAdapter extends BaseAdapter{
    private final List<Livro> livros;
    private final Context context;


    public LivrosAdapter(Context context, List<Livro> livros){
        this.context = context;
        this.livros = livros;
    }

    @Override
    public int getCount() {
        return livros.size();
    }

    @Override
    public Object getItem(int position) {
        return livros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return livros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Livro livro = livros.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.layout_padrao, parent, false);
        }

        ImageView imagemCapa = (ImageView) view.findViewById(R.id.imagemCapa);
        imagemCapa.setImageURI(Uri.parse(livro.getImagemCapa()));

        TextView titulo = (TextView) view.findViewById(R.id.tituloLista);
        titulo.setText(livro.getTitulo().toString());

        TextView autor = (TextView) view.findViewById(R.id.autorLista);
        autor.setText(livro.getAutor().toString());

        return view;
    }
}
