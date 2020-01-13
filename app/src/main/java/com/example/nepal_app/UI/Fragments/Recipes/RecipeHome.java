package com.example.nepal_app.UI.Fragments.Recipes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nepal_app.Logic.Adaptor.CategoryAdapter;
import com.example.nepal_app.Logic.CategoryObject;
import com.example.nepal_app.Logic.RecipeHomeObject;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeHome extends Fragment {

    List<CategoryObject> categoryList;
    List<Integer> btnIcons;
    List<RecipeHomeObject> recipeList, chickenList;
    Button btnViewRecipe, btnFavorite;

    public void fillLists() {
        recipeList = new ArrayList<>();
        categoryList = new ArrayList<>();
        chickenList = new ArrayList<>();
        btnIcons = new ArrayList<>();

        recipeList.add(new RecipeHomeObject("Banana", R.drawable.recipehome_bananas, btnViewRecipe, btnFavorite));
        recipeList.add(new RecipeHomeObject("Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));
        recipeList.add(new RecipeHomeObject("Cake", R.drawable.recipehome_cake, btnViewRecipe, btnFavorite));
        recipeList.add(new RecipeHomeObject("Dal", R.drawable.recipehome_dal, btnViewRecipe, btnFavorite));

        chickenList.add(new RecipeHomeObject("Delicious Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));
        chickenList.add(new RecipeHomeObject("Yummy Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));
        chickenList.add(new RecipeHomeObject("Exquisit Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));
        chickenList.add(new RecipeHomeObject("Mouth Watering Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));
        chickenList.add(new RecipeHomeObject("Look at that Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));
        chickenList.add(new RecipeHomeObject("I could eat this Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));
        chickenList.add(new RecipeHomeObject("MORE Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));

        categoryList.add(new CategoryObject("Recommended", recipeList));
        categoryList.add(new CategoryObject("Favorites", recipeList));
        categoryList.add(new CategoryObject("Snacks", chickenList));
        categoryList.add(new CategoryObject("Common", recipeList));
        categoryList.add(new CategoryObject("Search", recipeList));

        btnIcons.add(R.drawable.ic_reho_recommended);
        btnIcons.add(R.drawable.ic_reho_heart);
        btnIcons.add(R.drawable.ic_reho_snack);
        btnIcons.add(R.drawable.ic_reho_common);
        btnIcons.add(R.drawable.ic_reho_search);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fillLists();
        final View v = inflater.inflate(R.layout.recipe_home, container, false);
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recipeRecView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final CategoryAdapter adapter = new CategoryAdapter(categoryList, btnIcons);
                c.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();

        return v;

    }


}