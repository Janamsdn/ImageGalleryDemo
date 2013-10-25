package net.viralpatel.android.imagegalleray;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ImageGalleryDemoActivity extends Activity implements OnClickListener {
    
	private Animation inAnimRight, outAnimLeft, inAnimLeft, outAnimRight;
	private static int RESULT_LOAD_IMAGE = 1;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        
        Button buttonLoadImage = (Button) findViewById(R.id.background);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				String[] filePathColumn = {getResources().getString(R.drawable.newyork_da),
//						   getResources().getString(R.drawable.newyork_idback),
//						   getResources().getString(R.drawable.newyork_idcard),
//						   getResources().getString(R.drawable.texas_idcarda)
//						   };
				Intent i = new Intent(
						Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				
				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});
    }
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			 String[] filePathColumn = { MediaStore.Images.Media.DATA };
			
//			//String[] filePathColumn = {getResources().getString(R.drawable.newyork_da),
//									   getResources().getString(R.drawable.newyork_idback),
//									   getResources().getString(R.drawable.newyork_idcard),
//									   getResources().getString(R.drawable.texas_idcarda)
//									   };
//			
		
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			
			ImageView imageView = (ImageView) findViewById(R.id.icon1);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		
		
    
    
    }

  

	
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}