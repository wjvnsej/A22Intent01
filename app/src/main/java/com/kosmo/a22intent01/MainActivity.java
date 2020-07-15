package com.kosmo.a22intent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //투표수를 저장할 정수형 배열
    int[] votes = new int[9];
    //ImageView 객체를 저장할 배열
    ImageView[] imageViews = new ImageView[9];
    //영화제목을 저장할 배열
    String[] titles = {"블랙펜서", "스파이더맨", "아이언맨", "인피니티워","앤트맨&와스프","인크레더블헐크", "시빌워", "윈터솔져", "토르나그나로크"};
    //ImageView의 리소스 아이디를 저장할 배열
    int[] resourceIds = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9};
    /*
    ImageView의 리소스 아이디를 저장할 배열
    안드로이드는 리소스 아이디를 내부적으로 처리할때 int형으로 사용한다.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //앱타이틀 설정(메인 엑티비티에서만 적용됨)
        setTitle("당신이 좋아하는 영화는?");
        setContentView(R.layout.activity_main);

        //이미지뷰 위젯얻어와서 리스너 부착(영화의 갯수만큼 9번 반복)
        for(int i=0; i<votes.length; i++){
            //배열의 인덱스로 사용할 변수, 익명클래스에서 사용되므로 final로 선언
            final  int index = i;
            //각 영화 이미지를 가져와서 저장하고....
            imageViews[i] = (ImageView)findViewById(resourceIds[i]);
            //이미지별로 리스너를 부착한다.
            imageViews[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //투표수는 5가 최대이므로 그미만이라면...
                    if(votes[index]<5){
                        //투표수를 +1증가한다.
                        votes[index]++;
                        //현재 투표수를
                        Toast.makeText(MainActivity.this, String.format("%s:%d표", titles[index], votes[index]), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "5점 최고점수입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            ((Button)findViewById(R.id.btn_result)).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(v.getContext(), ResultActivity.class);
                    intent.putExtra("votes", votes);
                    intent.putExtra("titles", titles);

                    startActivity(intent);
                }
            });
        }
    }
}