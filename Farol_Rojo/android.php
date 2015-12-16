      
      
 <LinearLayout
            android:orientation="horizontal"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <ScrollView
                android:layout_width="match_parent"
                android:layout_height="855dp"
                android:id="@+id/scrollView"
                android:fillViewport="false"
                android:layout_gravity="right|top"
                android:layout_marginTop="30dp">
    
                <RelativeLayout
                    android:layout_width="match_parent"
                    android:layout_height="855dp"
                    android:layout_gravity="center_horizontal|top">
    
    
                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textAppearance="?android:attr/textAppearanceMedium"
                        android:text="Reservacion de Restaurante"
                        android:id="@+id/textView4"
                        android:layout_alignParentTop="true"
                        android:layout_centerHorizontal="true"
                        android:layout_marginTop="30dp" />
    
                    <ListView
                        android:layout_width="wrap_content"
                        android:layout_height="128dp"
                        android:id="@+id/listView"
                        android:layout_alignParentStart="false"
                        android:layout_below="@+id/textView4" />
    
                    <View
                        android:layout_width="fill_parent"
                        android:layout_height="3dp"
                        android:background="@android:color/black"
                        android:layout_marginTop="151dp"
                        android:id="@+id/view"
                        android:layout_below="@+id/view2"
                        android:layout_alignParentStart="true" />
    
                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textAppearance="?android:attr/textAppearanceMedium"
                        android:text="Taxis pedidos"
                        android:id="@+id/textView7"
                        android:layout_alignTop="@+id/view2"
                        android:layout_centerHorizontal="true" />
    
                    <ListView
                        android:layout_width="wrap_content"
                        android:layout_height="128dp"
                        android:id="@+id/listView2"
                        android:layout_below="@+id/textView7"
                        android:layout_alignParentStart="true" />
                    <View
                        android:layout_width="fill_parent"
                        android:layout_height="3dp"
                        android:background="@android:color/black"
                        android:layout_below="@+id/listView"
                        android:layout_alignParentStart="true"
                        android:layout_marginTop="12dp"
                        android:id="@+id/view2" />
    
                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textAppearance="?android:attr/textAppearanceMedium"
                        android:text="Servicios de Gimnasio"
                        android:id="@+id/textView10"
                        android:layout_alignTop="@+id/view"
                        android:layout_centerHorizontal="true" />
    
                    <ListView
                        android:layout_width="wrap_content"
                        android:layout_height="128dp"
                        android:id="@+id/listView3"
                        android:layout_below="@+id/textView10"
                        android:layout_alignParentStart="true" />
                    <View
                        android:layout_width="fill_parent"
                        android:layout_height="3dp"
                        android:background="@android:color/black"
                        android:id="@+id/view3"
                        android:layout_marginTop="158dp"
                        android:layout_below="@+id/view4"
                        android:layout_alignParentStart="true" />
    
                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textAppearance="?android:attr/textAppearanceMedium"
                        android:text="Alarmas Establecidas"
                        android:id="@+id/textView11"
                        android:layout_below="@+id/view4"
                        android:layout_alignEnd="@+id/textView10" />
    
                    <ListView
                        android:layout_width="wrap_content"
                        android:layout_height="128dp"
                        android:id="@+id/listView4"
                        android:layout_below="@+id/textView11"
                        android:layout_alignParentStart="true" />
                    <View
                        android:layout_width="fill_parent"
                        android:layout_height="3dp"
                        android:background="@android:color/black"
                        android:id="@+id/view4"
                        android:layout_below="@+id/listView3"
                        android:layout_alignParentStart="true"
                        android:layout_marginTop="9dp" />
    
                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:textAppearance="?android:attr/textAppearanceMedium"
                        android:text="Salida Programada"
                        android:id="@+id/textView12"
                        android:layout_alignTop="@+id/view3"
                        android:layout_centerHorizontal="true" />
    
                    <ListView
                        android:layout_width="wrap_content"
                        android:layout_height="128dp"
                        android:id="@+id/listView5"
                        android:layout_below="@+id/textView12"
                        android:layout_centerHorizontal="true" />
    
                </RelativeLayout>
            </ScrollView>
        </LinearLayout>