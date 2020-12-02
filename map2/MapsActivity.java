package com.example.tourmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, AutoPermissionsListener {
    Spinner spSeoulTour;
    private GoogleMap mMap;
    String seoulTour[] = {"국립중앙박물관", "남산골한옥마을", "예술의전당", "청계천",
            "63빌딩", "남산타워", "경복궁", "김치문화체험관", "서울올림픽기념관",
            "국립민속박물관", "서대문형무소역사관", "창덕궁"};
    double[] lat = {37.5240867, 37.5591447, 37.4785361, 37.5696512, 37.5198158, 37.5511147, 37.5788408, 37.5629457, 37.5202976, 37.5815645, 37.5742887, 37.5826041,
    }; //위도
    double[] lon = {126.9803881, 126.9936826, 127.0107423, 127.0056375, 126.9403139, 126.9878596, 126.9770162, 126.9851652, 127.1159236, 126.9789313, 126.9562269, 126.9919376,
    }; //경도

    double[] latlon = new double[2];
    double[] mylatlon = new double[2]; //나의 위치 위도 경도 담는 변수
    ArrayAdapter<String> adapter;
    int pos;
    LocationManager myLocation;
    LocationListener myListener;
    boolean myCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        AutoPermissions.Companion.loadAllPermissions(this, 101); //모든 퍼미션 다 체크해줌.
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        spSeoulTour = (Spinner) findViewById(R.id.spSeoulTour);
        setTitle("서울관광안내");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //CallBack:onCreate가 다 실행 후 onMapReady가 실행됨.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, seoulTour);
        spSeoulTour.setAdapter(adapter);
        spSeoulTour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                latlon[0] = lat[position];
                latlon[1] = lon[position];
                pos = position;
                myCheck = false;
                setMylatlon();
                moveMap(latlon);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();
        setMylatlon();
    }*/

    @SuppressLint("MissingPermission")
    void setMylatlon() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);//GPS, Network PROVIDER 둘 다 되는 PASSIVE
            if (location != null) {
                mylatlon[0] = location.getLatitude();
                mylatlon[1] = location.getLongitude();

            } else {
                showToast("내 위치 찾는 중....");
            }
        } catch (SecurityException e) {
            showToast("위치를 찾을 수 없습니다.");
        }
        GPSListener gpsListener = new GPSListener();
        //퍼미션 무시하기 누른다. 앱 실행 전이므로 오토퍼미션라이브러리 인식을 아직 못하므로 나오는 빨간선.
        manager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 1, gpsListener);
    }

    void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //옵션메뉴 만들기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "일반지도");
        menu.add(0, 2, 0, "위성지도");
        menu.add(0, 3, 0, "내위치찾기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 3: //내위치찾기 선택했을때
                myCheck = true;
                moveMap(mylatlon);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*그대로 두면 앱을 켜고 시작 위치가 시드니로 되어있게됨.*/
    }

    public void moveMap(double locationLatLon[]) {
        String[] address = {"서울특별시 용산구 서빙고로 137 국립중앙박물관",
                "서울특별시 중구 퇴계로34길 28 남산한옥마을",
                "서울특별시 서초구 남부순환로 2364 국립국악원",
                "서울특별시 종로구 창신동",
                "서울특별시 영등포구 63로 50 한화금융센터_63",
                "서울특별시 용산구 남산공원길 105 N서울타워",
                "서울특별시 종로구 삼청로 37 국립민속박물관",
                "서울특별시 중구 명동2가 32-2",
                "서울특별시 송파구 올림픽로 448 서울올림픽파크텔",
                "서울특별시 종로구 삼청로 37 국립민속박물관",
                "서울특별시 서대문구 통일로 251 독립공원",
                "서울특별시 종로구 율곡로 99"};
        final String[] tel = {"02-2264-4412", "02-580-1300", "02-2290-6114",
                "02-789-5663", "02-3455-9277", "02-3700-3900", "02-318-7051",
                "02-410-1354", "02-3704-3114", "02-360-8590", "02-762-8261"};
        final String[] homepage = {"http://www.museum.go.kr",
                "http://hanokmaeul.seoul.go.kr",
                "http://www.sac.or.kr",
                "http://www.cheonggyecheon.or.kr",
                "http://www.63.co.kr",
                "http://www.nseoultower.com",
                "http://www.royalpalace.go.kr",
                "http://www.visitseoul.net/kr/article/article.do?_method=view&art_id=49160&lang=kr&m=0004003002009&p=03",
                "http://www.88olympic.or.kr",
                "http://www.nfm.go.kr",
                "http://www.sscmc.or.kr/culture2",
                "http://www.cdg.go.kr"};
        LatLng TourPos = new LatLng(locationLatLon[0], locationLatLon[1]);
        //mMap.addMarker(new MarkerOptions().position(TourPos).title(seoulTour[position])); //(기본마커)이 위치가 마커로 표시됨.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TourPos, 15)); //지도가 이 위치로 이동:v:10으로 하면 서울 안 정도
        MarkerOptions markerOpt = new MarkerOptions(); //내맘대로 마커 만들기
        markerOpt.position(TourPos);
        if (myCheck == true) {
            markerOpt.title("내가 서 있는 곳");
            markerOpt.snippet("위도:" + locationLatLon[0] + " 경도:" + locationLatLon[1]);
        } else {
            markerOpt.title(address[pos]); //마커의 제목
            markerOpt.snippet(tel[pos]); //타이틀의 보조설명
        }

        markerOpt.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)); //마커의 아이콘 정하기.
        mMap.addMarker(markerOpt).showInfoWindow(); //만든 마커를 화면에 보이게.
        //마커를 클릭하면 해당 홈페이지로 이동.
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (myCheck == false) {
                    Uri uri = Uri.parse(homepage[pos]);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                return false;
            }
        });
        //타이틀을 클릭하면 암시적인텐트로 전화걸기.
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (myCheck == false) {
                    Uri uri = Uri.parse("tel:" + tel[pos]); //"tel:"을 꼭 써줘야 전화걸기 화면으로 넘어감
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onDenied(int i, String[] strings) {
        //showToast("허용 거부했습니다.");
    }

    @Override
    public void onGranted(int i, String[] strings) {
        showToast("사용 가능");
        setMylatlon();
    }
    //내 위치정보에 관련된 클래스 //LocationListener는 내 위치 정보를 가져오는 메서드 가짐.
    public class GPSListener implements LocationListener{

        @Override
        public void onLocationChanged(@NonNull Location location) {
            //내 위치의 위도 경도
            mylatlon[0] = location.getLatitude();
            mylatlon[1] = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:
                    showToast("위치 찾기 불가능지역");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    showToast("위치 찾기 일시 불가능");
                    break;
                case LocationProvider.AVAILABLE:
                    showToast("위치 찾기 서비스 사용 가능");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            showToast("현재 위치 서비스 가능 상태");
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            //위치 찾기 꺼놨을때
            showToast("현재 위치 서비스 불가능 상태. GPS 켜주세요");
        }
    }
}