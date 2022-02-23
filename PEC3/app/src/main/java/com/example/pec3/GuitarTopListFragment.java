package com.example.pec3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuitarTopListFragment extends Fragment {

	private RecyclerView mRecycler;
	private GuitarAdapter mAdapter;
	private Toolbar mToolbar;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	//clase que contiene las vistas de cada elemento en la lista
	private class GuitarHolder extends RecyclerView.ViewHolder{

		//variables miembro
		private ImageView mPhotoImageView;
		private TextView mNameTextView;
		private TextView mRatingTextView;
		private Guitar mGuitar;

		//constructor
		public GuitarHolder(LayoutInflater inflater, ViewGroup parent) {
			super(inflater.inflate(R.layout.list_item_guitar, parent,false));
			//captura los View
			mPhotoImageView = (ImageView) itemView.findViewById(R.id.photo_imageview);
			mNameTextView = (TextView) itemView.findViewById(R.id.name_textview);
			mRatingTextView = (TextView) itemView.findViewById(R.id.rating_textview);
		}

		//este metodo setea las variables miembro
		public void bind(Guitar guitar){
			mGuitar = guitar;
			//setea los View
			mPhotoImageView.setImageBitmap(GuitarLab.transformImageToBitmap(mGuitar.getmImage()));
			mNameTextView.setText(mGuitar.getmName());
			mRatingTextView.setText(Integer.toString(mGuitar.getmRating()));
		}
	}

	//clase que vincula los datos con las vistas
	private class GuitarAdapter extends RecyclerView.Adapter<GuitarHolder>{

		//variables miembro
		private List<Guitar> mGuitars;

		//constructor
		public GuitarAdapter(List<Guitar> guitars){
			mGuitars = guitars;
		}

		@NonNull
		@Override
		public GuitarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			return new GuitarHolder(layoutInflater,parent);
		}

		@Override
		public void onBindViewHolder(@NonNull GuitarHolder holder, int position) {
			//obtiene el objeto
			Guitar guitar = mGuitars.get(position);
			//vincula los datos con la vista llamando al metodo bind del la clase GuitarHolder
			holder.bind(guitar);
		}

		@Override
		public int getItemCount() {
			return mGuitars.size();
		}
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_guitar_top_list,container,false);
		//elimino el titulo de la activity
		getActivity().setTitle(null);
		//capturo la toolbar
		mToolbar = (Toolbar) view.findViewById(R.id.toolbar_list_guitar);
		//creo un soporte lara la toolbar
		((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
		//habilito el botón para volver a la actividad padre
		((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		//capturo el RecyclerView
		mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_list_2);
		//setea un layout para el contenido
		mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

		//obtiene una lista de objetos Guitar
		List<Guitar> guitars = GuitarLab.get(getActivity()).getTopGuitars();
		//crea un adaptador
		mAdapter = new GuitarAdapter(guitars);
		//setea el adaptador
		mRecycler.setAdapter(mAdapter);

		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_guitar_top_list,menu);
	}
}
