package com.example.firebase_conexion.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase_conexion.cuestionario.MainModel;
import com.example.firebase_conexion.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter_Admin2 extends FirebaseRecyclerAdapter<MainModel,MainAdapter_Admin2.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter_Admin2(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull MainModel model) {
        holder.modelo.setText(model.getModelo());
        holder.descripcion.setText(model.getDescripcion());
        holder.compra.setText(model.getCompra());

        Glide.with(holder.img.getContext())
                .load(model.getFoto())
                .placeholder((R.drawable.common_google_signin_btn_icon_dark))
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);


        holder.btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext()).setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1210).create();
                //dialogPlus.show();

                View view=dialogPlus.getHolderView();
                EditText modelo=view.findViewById(R.id.txtModelo);
                EditText descripcion=view.findViewById(R.id.txtDescripcion);
                EditText compra=view.findViewById(R.id.txtCompra);
                EditText foto=view.findViewById(R.id.txtFoto);
                Button btnActulizar=view.findViewById(R.id.btnActualizar);


                modelo.setText(model.getModelo());
                descripcion.setText(model.getDescripcion());
                compra.setText(model.getCompra());
                foto.setText(model.getFoto());

                dialogPlus.show();

                btnActulizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("modelo",modelo.getText().toString());
                        map.put("foto",foto.getText().toString());
                        map.put("descripcion",descripcion.getText().toString());
                        map.put("compra",compra.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("movil_tope").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(holder.modelo.getContext(), "Datos Actualizados correctamente", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.modelo.getContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });

        holder.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.modelo.getContext());
                builder.setTitle("¿Estas seguro?");
                builder.setMessage("No se podra recuperar la información borrada.");
                builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                FirebaseDatabase.getInstance().getReference().child("movil_tope")
                        .child(getRef(position).getKey()).removeValue();

            }
        });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.modelo.getContext(), "Cambios cancelados", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_admin,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView modelo,descripcion,compra;
        Button btnActualizar,btnBorrar;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            modelo=(TextView)itemView.findViewById(R.id.nametext);
            descripcion=(TextView)itemView.findViewById(R.id.coursetext);
            compra=(TextView)itemView.findViewById(R.id.emailtext);

            btnActualizar=(Button)itemView.findViewById(R.id.btnEdit);
            btnBorrar=(Button)itemView.findViewById(R.id.btnDelete);
        }
    }

}