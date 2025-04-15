package fi.arcada.p01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fi.arcada.codechallenge.R;

// För att använda en RecyclerView måste man göra till den en adapter som hanterar denns data
// ((Precis som man behöver en ström adapter för vissa maskiner))
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    // Vi vill använda vår model från tidigare
    private List<DataModel> dataList;

    // Constructor : initializera adaptern med vår dataList (model)
    public MyAdapter(List<DataModel> dataList) {
        this.dataList = dataList;
    }

    // Adaptern håller ordning på RecyclerView:ns "rader" som hålls i en ViewHolder
    // Varje gång vi gör en ny "rad" skapar vi en ny ViewHolder
    // Denna ViewHolder definerar vi med denna inre klass
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // ViewHoldern har, som vi gjorde i item_layout.xml, två TextView element
        public TextView textView1;
        public TextView textView2;


        public MyViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
        }
    }

    // När vi gör en ny ViewHolder ("onCreate"ViewHolder) så hämtar vi vår layout (item_layout.xml)
    // Sen utökar vi vår RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    // När vi gör en ny ViewHolder har vi sagt att den ska ha textView object
    // Men vi måste binda allt detta samman så vi får en komplett ViewHolder som har just de textViews vi just utökade med
    // Dessa textViews ska även referera till korrekt värden i dataListan
    // I.e., vi måste binda ihop allt så det blir ordning och reda!

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataModel data = dataList.get(position);
        holder.textView1.setText(data.getValue1());
        holder.textView2.setText(data.getValue2());
    }

    // Eftersom vi gör en "extends" har ReccyclerView.Adapter krav på en getItemCount()
    // Eftersom vi vet att vår RecyclerView håller en DataModel som heter dataList och det bara är en ArrayList
    // Är det enkelt att bestäma nu direkt att det är .size() : Om vi hade någon mer komplicerad model av lista
    // Kan det hända att vi måste göra en mer invecklad funktion, t.ex. om vi hade en nested-list
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

