package com.example.tourmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
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
    String seoulTour[] = {"국립중앙박물관", "남산골한옥마을", "예술의전당", "청계천", "63빌딩", "남산타워", "경복궁", "김치문화체험관", "서울올림픽기념관", "국립민속박물관", "서대문형무소역사관", "창덕궁"
    };
    Double[] lat = {37.5240867, 37.5591447, 37.4785361, 37.5696512, 37.5198158, 37.5511147, 37.5788408, 37.5629457, 37.5202976, 37.5815645, 37.5742887, 37.5826041
    };
    Double[] lon = {126.9803881, 126.9936826, 127.0107423, 127.0056375, 126.9403139, 126.9878596, 126.9770162, 126.9851652, 127.1159236, 126.9789313, 126.9562269, 126.9919376
    };
    Double latlon[] = new Double[2];
    Double mylatlon[] = new Double[2]; //내 위치 담음
    ArrayAdapter<String> adapter;
    int pos;
    boolean myCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        AutoPermissions.Companion.loadAllPermissions(this, 101);
        spSeoulTour = findViewById(R.id.spSeoulTour);
        setTitle("tour");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, seoulTour);
        spSeoulTour.setAdapter(adapter);
        spSeoulTour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //position은 스피너 위부터 0에서 시작
                latlon[0] = lat[position];
                latlon[1] = lon[position];
                pos = position;
                myCheck = false;
                moveMap(latlon);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "일반지도");
        menu.add(0, 2, 0, "위성지도");
        menu.add(0, 3, 0, "내 위치");
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
            case 3:
                myCheck = true;
                moveMap(mylatlon);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void moveMap(Double location[]) {
        String[] addr = {"용산구 서빙고로 137 국립중앙박물관",
                "중구 퇴계로34길 28 남산한옥마을",
                "서초구 남부순환로 2364 국립국악원",
                "종로구 창신동",
                "영등포구 63로 50 한화금융센터_63",
                "용산구 남산공원길 105 N서울타워",
                "종로구 삼청로 37 국립민속박물관",
                "중구 명동2가 32-2",
                "송파구 올림픽로 448 서울올림픽파크텔",
                "종로구 삼청로 37 국립민속박물관",
                "서대문구 통일로 251 독립공원",
                "종로구 율곡로 99"
        };
        final String[] tel = {"02-2077-9000",
                "02-2264-4412",
                "02-580-1300",
                "02-2290-6114",
                "02-789-5663",
                "02-3455-9277",
                "02-3700-3900",
                "02-318-7051",
                "02-410-1354",
                "02-3704-3114",
                "02-360-8590",
                "02-762-8261"
        };
        final String[] hp = {"http://www.museum.go.kr",
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
                "http://www.cdg.go.kr"
        };
        LatLng spot = new LatLng(latlon[0], latlon[1]);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spot, 15));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(spot);
        if (myCheck == true) {
            markerOptions.title("내 위치");
            markerOptions.snippet("위도:" + mylatlon[0] + "경도: " + mylatlon[1]);
        } else {
            markerOptions.title(addr[pos]);
            markerOptions.snippet(tel[pos]);
        }

        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        mMap.addMarker(markerOptions).showInfoWindow();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Uri uri = Uri.parse(hp[pos]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return false;
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Uri uri = Uri.parse("tel:" + tel[pos]);//"Tel" 로 하면 꺼짐 대소문자 구별하기
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDenied(int i, String[] strings) {
        //퍼미션 거부함 msg
    }

    @Override
    public void onGranted(int i, String[] strings) {
        //퍼미션 허용함 msg
        setMylatlon();
    }

    void setMylatlon() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);//마지막 위치정보 꺼냄
            if (location != null) {
                mylatlon[0] = location.getLatitude();
                mylatlon[1] = location.getLongitude();
            } else {
                //위치 찾는중 msg
            }
        } catch (SecurityException e) {
            //위치 못찾음 msg
        }
        GPSListener gpsListener = new GPSListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        manager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 1, gpsListener);
        //시간, 거리 바뀔 때마다 위치 정보 가져옴?
    }

    public class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            mylatlon[0] = location.getLatitude();
            mylatlon[1] = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:
                    //위치 찾기 불가능 지역
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    //잠시 불가능
                    break;
                case LocationProvider.AVAILABLE:
                    //사용가능
                    break;
            }
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {//위치 서비스 가능  msg
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {//불가능 msg
        }
    }
}