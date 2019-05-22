package th.ac.kku.thongnoi.matavut;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecycleView_Config {
    private Context mContext;
    private BooksAdapter mBooksAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Book> books, List<String> keys){
        mContext = context;
        mBooksAdapter = new BooksAdapter(books, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdapter);
    }

    class BookItemView extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private TextView tv_item;
        private TextView tv_time;

        private String key;

        public BookItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.book_list_item2, parent, false));

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }

        public void bind(Book book, String key){
            tv_title.setText(book.getTitle());
            tv_item.setText(book.getItem());
            tv_time.setText(book.getTime());
            this.key = key;
        }

    }

    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Book> mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Book> mBookList, List<String> mKeys){
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mBookList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
