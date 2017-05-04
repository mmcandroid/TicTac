package com.example.malek.tictac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements IClickListenr {
    private boolean currentX = true;
    private Adapter adapter;
    private List<Casse> casses;
    private int[][] matrix = new int[3][3];
    private RecyclerView recyclerView;
    private int[] winXVector = new int[]{1, 1, 1};
    private int[] winOVector = new int[]{-1, -1, -1};
    private enum win{

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        casses = new ArrayList<>();
        Button reset = (Button) findViewById(R.id.reset);
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentX = true;
                casses.clear();
                init();
            }
        });
        init();
    }

    public void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Casse casse = new Casse();
                casse.setRow(i);
                casse.setCol(j);
                casse.setValue("");
                casses.add(casse);
                matrix[i][j] = 0;
            }
        }
        Log.d("size", String.valueOf(casses.size()));
        adapter = new Adapter(this, casses);
        adapter.setiClickListenr(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void itemClicked(int postion) {
        Casse casse = casses.get(postion);
        if (!casse.isHasValue()) {
            casse.setValue(currentX ? "X" : "O");
            casses.set(postion, casse);
            matrix[casse.getRow()][casse.getCol()] = casse.getValueMatrix();
            currentX = !currentX;
            Log.e("detect",detectWinner()+"");
            adapter.update(casses);
        }
    }

    public int detectWinner() {
        int sumDiag = 0;
        int sumOtherDiag = 0;
        int[][] matrixTraspose = transposeMatrix(matrix);
        for (int i = 0; i < 3; i++) {
            Log.d("matrix[i]", Arrays.toString(matrix[i]) +"");
            if (Arrays.toString(matrix[i]).equals(Arrays.toString(winXVector)) ||Arrays.toString(matrixTraspose[i]).equals(Arrays.toString(winXVector))) {
                return 1;
            }
            if (Arrays.toString(matrix[i]).equals(Arrays.toString(winOVector)) ||Arrays.toString(matrixTraspose[i]).equals(Arrays.toString(winOVector))) {
                return -1;
            }
            sumDiag = matrix[i][i] + sumDiag;
            if (sumDiag == 3)
                return 1;
            else if (sumDiag == -3)
                return -1;
            sumOtherDiag = matrix[i][2 - i] + sumOtherDiag;
            if (sumOtherDiag == 3)
                return 1;
            else if (sumOtherDiag == -3)
                return -1;

    }
    return 0;
}

    public int[][] transposeMatrix(int[][] m) {
        int[][] temp = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }
}
