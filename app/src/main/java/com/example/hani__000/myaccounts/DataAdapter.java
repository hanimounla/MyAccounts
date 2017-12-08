package com.example.hani__000.myaccounts;

/**
 * Created by hani-_000 on 2017-11-28.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter<Account> {

    Context context;
    ArrayList<Account> mcontact;
    View view;

    public DataAdapter(Context context, ArrayList<Account> account){
        super(context, R.layout.fragment_all_accounts, account);
        this.context = context;
        this.mcontact = account;
    }

    public class Holder{
        TextView id = (TextView)view.findViewById(R.id.accountIDLBL);
        TextView nameFV = (TextView) view.findViewById(R.id.accountNameLBL);
        ImageView pic = (ImageView) view.findViewById(R.id.accountImage);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Account data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null)
        {
            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_all_accounts, parent, false);

//            viewHolder.id = (TextView)view.findViewById(R.id.accountIDLBL);
//            viewHolder.nameFV = (TextView) view.findViewById(R.id.accountNameLBL);
//            viewHolder.pic =  (ImageView) view.findViewById(R.id.accountImage);

            convertView.setTag(viewHolder);

        }
        else
            viewHolder = (Holder) convertView.getTag();


        assert data != null;
        int AccountId  = data.getAccountID();
        viewHolder.id.setText(AccountId + "");
        viewHolder.nameFV.setText(data.getWebSite());
        viewHolder.pic.setImageBitmap(convertToBitmap(data.getImage()));

        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array

    private Bitmap convertToBitmap(byte[] b){
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
}
