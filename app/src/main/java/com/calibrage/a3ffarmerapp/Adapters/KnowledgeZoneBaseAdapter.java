package com.calibrage.a3ffarmerapp.Adapters;





        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.calibrage.a3ffarmerapp.Model.Album;
        import com.calibrage.a3ffarmerapp.Model.GetLookUpModel;
        import com.calibrage.a3ffarmerapp.R;

        import java.util.List;

public class KnowledgeZoneBaseAdapter extends BaseAdapter {

    private  Context mContext;
    private List<GetLookUpModel.ListResult> books;
    int[] covers = new int[]{
            R.drawable.encylopedia,
            R.drawable.encylopedia,
            R.drawable.encylopedia,
            R.drawable.encylopedia,
            R.drawable.encylopedia,
            R.drawable.encylopedia,
           /* R.drawable.warehouse,
            R.drawable.warehouse,
            R.drawable.category1,
            R.drawable.category2,
            R.drawable.category3,*/
            /*  R.drawable.album5,
              R.drawable.album6,
              R.drawable.album7,
              R.drawable.album8,
              R.drawable.album9,
              R.drawable.album10,
              R.drawable.album11*/};
    // 1
    public KnowledgeZoneBaseAdapter(Context context, List<GetLookUpModel.ListResult> books) {
        this.mContext = context;
        this.books = books;
    }

    // 2
    @Override
    public int getCount() {
        return books.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final GetLookUpModel.ListResult book = books.get(position);

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.adapter_kz_home, null);
        }

        // 3
        final ImageView imageView = convertView.findViewById(R.id.imageView);
        final TextView textView = convertView.findViewById(R.id.text);


        // 4
        //  imageView.setImageResource(R.drawable.encylopedia);
        try {
            textView.setText(book.getName());
            imageView.setImageResource(covers[position]);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

}

