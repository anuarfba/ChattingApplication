package com.example.chatwithme.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatwithme.Model.MessageModel;
import com.example.chatwithme.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class chatAdapter  extends RecyclerView.Adapter{

    ArrayList<MessageModel> messageModels;
    Context context;

    String recId;
    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE= 2;

    public chatAdapter(ArrayList<MessageModel> messageModels, Context context, String recId) {
        this.messageModels = messageModels;
        this.context = context;
        this.recId = recId;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      if (viewType == SENDER_VIEW_TYPE){
          View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
          return new SenderViewHolder(view);
      }
      else {
          View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);

          return new ReceiverViewHolder(view);
      }


    }

    @Override
    public int getItemViewType(int position) {
      if (messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){

          return SENDER_VIEW_TYPE;

      }
      else {
          return RECEIVER_VIEW_TYPE;
      }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageModel messageModel = messageModels.get(position);

        if (holder.getClass()==SenderViewHolder.class){
           ((SenderViewHolder) holder).senderMsg.setText(messageModel.getMessase());
        }
        else {
            ((ReceiverViewHolder)holder).receiverMsg.setText(messageModel.getMessase());
        }

    }

    @Override
    public int getItemCount() {
        return messageModels.size() ;

    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        TextView receiverMsg , receiverTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            receiverMsg = itemView.findViewById(R.id.receiverText);
            receiverTime = itemView.findViewById(R.id.ReceiveTime);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView senderMsg, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);
        }
    }
}
