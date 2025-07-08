package com.example.recycling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder> {

    private Context context;
    private List<Word> words;

    public DictionaryAdapter(List<Word> words, Context context) {
        this.words = words;
        this.context = context;
    }

    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.dictionary_item_view, parent, false);
        return new DictionaryViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {
        Word word = words.get(position);
        holder.setData(word);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static class DictionaryViewHolder extends RecyclerView.ViewHolder {

        private TextView tvWord;
        private TextView tvDefinition;
        private Context context;

        public DictionaryViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            bindingView();
            bindingAction();
        }

        private void bindingView() {
            tvWord = itemView.findViewById(R.id.tvWord);
            tvDefinition = itemView.findViewById(R.id.tvDevination); // ✅ Sửa ID đúng chính tả
        }

        private void bindingAction() {
            tvWord.setOnClickListener(this::onTvClick);
        }

        private void onTvClick(View view) {
            Toast.makeText(context, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }

        public void setData(Word word) {
            tvWord.setText(word.getWord());
            tvDefinition.setText(word.getMeaning());
        }
    }
}
