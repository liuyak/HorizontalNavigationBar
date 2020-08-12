
# 使用方法：

1. App目录下build.gradle添加

   compile 'com.liuyk.widget:horizontalnavigation:1.0.8'

2. 项目根目录下build.gradle添加
 

    allprojects {
    
         repositories {
    
             jcenter()
    
             maven {
    
                  url 'https://dl.bintray.com/liuyak1004/maven'
             }
        }
    }

# 案例代码
 

    <com.liuyk.widget.HorizontalNavigationBar
            android:id="@+id/horizontal_navigation"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:background="#f2f2f2"
            android:scrollbars="none" />
        

Java部分

 private void initView() {
         mHorizontalNavigationBar = findViewById(R.id.horizontal_navigation);
         mHorizontalNavigationBar.addOnHorizontalNavigationSelectListener(this);
         adapter = new ChannelAdapter();
         adapter.setItems(getData());
         mHorizontalNavigationBar.setAdapter(adapter);
         mHorizontalNavigationBar.setCurrentChannelItem(0);
     }

     private ArrayList<Channel> getData() {
         final ArrayList<Channel> items = new ArrayList<>();
         for (int i = 0; i < 15; i++) {
             final Channel channel = new Channel();
             if (i == 0) {
                 channel.isSelect = true;
             }
             channel.setChannelName("选项" + (i + 1));
             items.add(channel);
         }
         return items;
     }
    

# bean

    public class Channel implements Serializable {
        private static final long serialVersionUID = -7415501530039818852L;
        private String channelName;
    
        public String getChannelName() {
            return channelName;
        }
    
        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }
    }


# HorizontalNavigationBar
新闻客户端的水平滚动导航条效果
![image](https://github.com/liuyak/HorizontalNavigationBar/raw/master/Screenshots.jpg)