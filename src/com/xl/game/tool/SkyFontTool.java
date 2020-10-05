package com.xl.game.tool;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SkyFontTool {
    private static final String TAG = "SkyFontTool";



/*
sky字体绘制
风的影子
*/

    class FONT {
        byte[] bitbuf;
        String filename;//字体文件名
        long f;//文件指针
        long font_size;//字号
        long ansi_size;//ansi字符宽度
        int width;
        int height;
    }

    ;

    class FONT_ANSI {
        char width[]; //128
        char height[]; //128


    }

    ;//所有ansi字符宽高

    FONT_ANSI font_ansi;

    FONT font_sky16;
    byte[] font_sky16_bitbuf = new byte[32]; //32
    String font_sky16_filename = "system/gb16_mrpoid.uc2";
    RandomAccessFile font_sky16_f;
    int font_sky16_font_size = 16;
    int font_sky16_ansi_size = 8;
    Canvas canvas;

    public SkyFontTool(RandomAccessFile accessFile)//字体初始化，打开字体文件
    {
        this.canvas = canvas;
        int id = 0;

        font_sky16 = new FONT();
        font_ansi = new FONT_ANSI();
        font_ansi.width = new char[128];
        font_ansi.height = new char[128];
//font_sky16_filename = "system/gb16_mrpoid.uc2";

font_sky16_f = accessFile;
        try {
            Log.i(TAG, "SkyFontTool: 文件长度："+accessFile.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        try {
            font_sky16_f = new RandomAccessFile(font_sky16_filename, "r");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */
        if (font_sky16_f == null) {

            Log.i(TAG, "字体加载失败");
            return;
        }

        Log.i(TAG, "xl_font_sky16_init: 字库加载成功");

        while (id < 128) {
            font_ansi.width[id] = 8;
            font_ansi.height[id] = 16;
            id++;
        }
        font_sky16_font_size = 16;

        font_sky16_ansi_size = 8;

    }

    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
    }

    public int xl_font_sky16_close()//关闭字体
    {
        try {
            font_sky16_f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "xl_font_sky16_close: 关闭字体");
        return 0;
    }

    void dpoint(int x, int y, Paint paint) {
//        Log.i(TAG, "dpoint: "+x+" "+y+" ");
        if(canvas!=null)
        canvas.drawPoint(x, y, paint);
    }

    //获取二进制缓存里指定像素的值
    int xl_font_sky16_getfontpix(byte buf[], int n) {
//计算在第几个字节，从0开始
        byte temp = buf[n / 8];
//计算在第几位n%8
        return (128 >> (n % 8)) & temp;

    }

    int getfontpix(byte buf[], int n)//获取字体第n个点信息
    {
//计算在第几个字节，从0开始
        byte temp = buf[n / 8];
//计算在第几位n%8
        return (128 >> (n % 8)) & temp;

    }


    //获得字符的位图
    byte[] xl_font_sky16_getChar(char id) {
        byte font[] = new byte[32];
        try {
            font_sky16_f.seek(id * 32);

            font_sky16_f.read(font);
//        mr_read(font_sky16_f, font_sky16_bitbuf, 32);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "xl_font_sky16_getChar: " + e.getMessage());
        }
        return font;

    }

    //画一个字
    public byte[] xl_font_sky16_drawChar(char id, int x, int y, int color) {
//        Log.i(TAG, "xl_font_sky16_drawChar: "+id);
//        byte[] font = new byte[32];
//        mr_seek(font_sky16_f, id * 32, 0);
//        mr_read(font_sky16_f, font_sky16_bitbuf, 32);
        try {
            font_sky16_f.seek(id * 32);
            font_sky16_f.read(font_sky16_bitbuf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int y2 = y + 16;//font_sky16_font_size;
        int n = 0;

        int ix = x;
        int iy;
        Paint paint = new Paint();
        paint.setColor(color);
        for (iy = y; iy < y2; iy++) {

            ix = x;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;

            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;
            if (getfontpix(font_sky16_bitbuf, n) >= 1)
                dpoint(ix, iy, paint);
            n++;
            ix++;

        }
        return font_sky16_bitbuf;
    }

    //画一行文字
    public void xl_font_sky16_drawText(Canvas canvas,String text, int x,int y,int color){
        Log.i(TAG, "xl_font_sky16_drawText: "+text+y);
        this.canvas = canvas;
        int ix = x;
        int iy = y;
        for(int i=0;i<text.length();i++){
            char c = text.charAt(i);
            xl_font_sky16_drawChar(c, ix,iy, color);
            int fontwh = xl_font_sky16_charWidthHeight(c);
            ix+= (fontwh>>16)&0xff;
        }
    }

    //获取一个文字的宽高
    int xl_font_sky16_charWidthHeight(char id) {
        int width = 0;
        int height = 0;
        if (id < 128) {
            width = font_ansi.width[id];
            height = font_ansi.height[id];
        } else {
            width = 16;
            height = 16;
        }
        return (width << 16) | height;
    }
    //获取一个文字的宽高
    int xl_font_sky16_charWidth(char id) {
        int width = 0;
        int height = 0;
        if (id < 128) {
            width = font_ansi.width[id];
            height = font_ansi.height[id];
        } else {
            width = 16;
            height = 16;
        }
        return (width);
    }

    //获取一个文字的宽高
    int xl_font_sky16_charHeight(char id) {
        int width = 0;
        int height = 0;
        if (id < 128) {
            width = font_ansi.width[id];
            height = font_ansi.height[id];
        } else {
            width = 16;
            height = 16;
        }
        return height;
    }

    //获取单行文字的宽高
    int xl_font_sky16_textWidthHeight(String text) {
        int width=0;
        int height = 0;
        int textIdx = 0;
        char id;
        int fontw = 0, fonth = 0;
        for (textIdx = 0;textIdx<text.length();textIdx++) {
            id = text.charAt(textIdx);
            int temp_wh = xl_font_sky16_charWidthHeight(id);
            fontw = temp_wh>>16 & 0xffff;
            fonth = temp_wh&0xffff;
            width+=fontw;
            height=fonth;
        }
        return (width<<16) | height;
    }


/*
//画文字，不支持换行
void xl_font_sky16_drawText(char *text0, int16 x0, int16 y0, uint8 r, uint8 g, uint8 b, int isunicode, int fontsize)
{
 long hnd = font_sky16_f;

 uint16 *screen=getscrbuf();
 uint8 wordBuf[32],//缓冲
 textIdx = 0, //字符偏移
 addW[2] = { 16, 8},//
  notChinaWord;//字符宽度

 int16 ix, iy, x = x0, y = y0,
 x2, y2 = y + 16,
 n,
  size = font_sky16_font_size*font_sky16_font_size>>3;// fntTp * fntTp >> 3;

 int textLen;//文本长度
 textLen= (isunicode)
 ? wstrlen(text0)
 : strlen(text0);
 int ch;//字符序号
 uint16 clr = (r << 16) + (g << 8) + b;//16位颜色信息
 char *pText = (isunicode==1)
 ? text0//(char *) c2u(text0, NULL, &textLen);
 : (char *)c2u(text0,NULL,&textLen);
 textLen=wstrlen(pText);
// sez16_sky("\x00\x64\x0\x0",x0, y0, r, g, b);
// sez16_sky(pText,x0,y0,r,g,b);


 while(textIdx < textLen)
 {

  ch = (pText[textIdx] << 8) + (pText[textIdx + 1]);
  //printf("%d ",ch);
  mr_seek(hnd, ch * size, 0);
  //memset(wordBuf, 0, 32);
  mr_read(hnd, &wordBuf, size);

  n = 0;
  if(ch<128)
  notChinaWord=1;// font_sky16_ansi_size;
  else
  notChinaWord=0;
  x2 = x+ font_sky16_font_size;

  for(iy = y; iy < y2; iy ++)
  for(ix = x; ix < x2; ix ++)
  {

   if(xl_font_sky16_getfontpix(wordBuf,n) )
   dpoint(ix,iy,clr);
   n++;
  }
  x += addW[notChinaWord];
  textIdx += 2;
 }
// if(isRef)
  //ref(x0, y0, x - x0, 16);
// free(pText);
}


int sez16_sky(char *unicode_text,int x,int y,int r0,int g0,int b0)
//显示sky文字
{
int fontsize=16;
//int sezjb=mr_open(font_sky16_filename,1);
//if(sezjb==0){return -1;}
int g,//预加载高
i,//预加载宽
b,//文字一部分的十近制值
a,//加载十进制文字参数
ys,//十六近制颜色
l,//字符串长度
ii,//循环每一字符
ws=10;
uint8 hce[32],//视为虚拟缓存
*hc=hce;//读取指针指向hce

y++;
ys=(r0>>3<<11)+(g0>>2<<5)+(b0>>3);
l=wstrlen(unicode_text);

for(ii=0;ii<l;ii+=2)
  {
a=unicode_text[ii+1]+(unicode_text[ii]<<8);
//a=unicode_text[ii]+(unicode_text[ii+1]<<8);
mr_seek(font_sky16_f,a*fontsize*fontsize/8,0);
mr_read(font_sky16_f,hc,fontsize*fontsize/8);
g=0;

  if(a<128)
    {
    while(g<fontsize)
      {
      b=hc[g*2];
      if(b)
        {
        for(i=0;i<fontsize/2;i++)
          {
          if(b<<24>>31!=0)//检测每一个二进制点
            {
            drect((i+x)*2,(g+y)*2,2,2,50,50,50);
            }
            b<<=1;
          }
        }
        g++;
      }
      x+=fontsize/2;
    }
  else
    {
    while(g<fontsize/2)
      {
      b= (hc[g*4]<<24)+ (hc[g*4+1]<<16)+ (hc[g*4+2]<<8) +hc[g*4+3];
      for(i=0;i<32;i++)
        {
        if(b%2)
          {
          drect((15-i%16+x)*2,(g*2-i/16+1+y)*2,2,2,50,50,50);
         }
    b>>=1;
        }

        g++;
      }
      x+=fontsize;
    }
  }
//mr_close(font_sky16_f);
return 0;
}
*/

/*
//画文字，不支持换行
void xl_font_sky16_drawText_un(char *text0, int16 x0, int16 y0, uint8 r, uint8 g, uint8 b,  int fontsize)
{
 long hnd = font_sky16_f;

 uint16 *screen=getscrbuf();
 uint8 wordBuf[32],//缓冲
 textIdx = 0, //字符偏移
 addW[2] = { 16, 8},//
  notChinaWord;//字符宽度

 int16 ix, iy, x = x0, y = y0,
 x2, y2 = y + 16,
 n,
  size = font_sky16_font_size*font_sky16_font_size>>3;// fntTp * fntTp >> 3;

 int textLen;//文本长度
 textLen=  wstrlen(text0);
 int ch;//字符序号
 uint16 clr =47059;// ((r << 8) & 0b1111100000000000) | ((g << 3 )& 0b11111100000) | (b>>3);//16位颜色信息
 char *pText =  text0;

 textLen=wstrlen(pText);
 if(textLen>200)textLen=200;
 printf("%d ",textLen);
//
sez16_sky("\x00\x64\x0\x0",x0, y0, r, g, b);
//
sez16_sky(pText,x0,y0,r,g,b);


 while(textIdx < textLen)
 {

  ch = ((*pText)<< 8) | *(pText+1);
  if(ch == 0x0d)
  {pText+=2;continue;}
  if( ch==0x0a)
  {
  x=x0; y+=18;y2=y+16; pText+=2;continue;
  }
  //printf("%d ",ch);
  mr_seek(hnd, ch * size, 0);
  //memset(wordBuf, 0, 32);
  mr_read(hnd, &wordBuf, size);

  n = 0;
  if(ch<128)
  notChinaWord=1;// font_sky16_ansi_size;
  else
  notChinaWord=0;
  x2 = x+ font_sky16_font_size;

  for(iy = y; iy < y+15; iy ++)
  for(ix = x; ix < x+16; ix ++)
  {


    if(xl_font_sky16_getfontpix(wordBuf,n) )
   dpoint(ix,iy,clr);
   n++;
  }
  x += addW[notChinaWord];


if(x>SCRW-16)
{
x=x0;y=y+18;y2=y+16;
}
  pText += 2;
  if(*pText==0 && *(pText+1)==0)
  break;
 }
// if(isRef)
  ref(x0, y0, x - x0, 16);
// free(pText);
}
*/




    /* 在指定区域绘文本，可换行
     * 参数:
     * x,y,w,h 文本矩行区
     * r,g,b 文本色
     * fType 字体类型
     * isRef 是否刷新
     * 注: 换行请用手机自带的换行不要用'\n'
     */
/*
void tt_DrawClrText(char *text, int16 x, int16 y, int16 w, int16 h, uint8 r, uint8 g, uint8 b, int isunicode,int fontsize)
{
int fType=16;
 if(text == NULL)
  return ;

 int16 idx = 0, tx = x, ty = y;

 int textLen = (isunicode)
 ? wstrlen(text)
 : strlen(text);

 char *pText = (char *) c2u(text, NULL, &textLen);
 xl_font_sky16_drawText_un(pText,x,y,r,g,b,1);



}
*/


}
