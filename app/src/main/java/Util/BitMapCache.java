package Util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Classe Responsavel por manter cache das informações de imagem em disco
 * Created by Roanderson on 02/03/2016.
 */
public class BitMapCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache{
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public BitMapCache(int maxSize) {
        super(maxSize);
    }
    public BitMapCache(){
        this(getDefaultCacheSize());
    }

  public static int getDefaultCacheSize(){
        final int memoriaMax=  (int)(Runtime.getRuntime().maxMemory()/1024);
        final int tamanhoCache = memoriaMax/8;

        return tamanhoCache;
  }
    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
            put(url,bitmap);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes()*value.getHeight()/1024;

    }
}
