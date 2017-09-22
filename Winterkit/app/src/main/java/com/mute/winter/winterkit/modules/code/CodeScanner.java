package com.mute.winter.winterkit.modules.code;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Collection;

/**
 * @author dkoller
 * @since 11.08.2016
 */
public class CodeScanner {

    private boolean beepEnabled = true;
    private boolean barcodeImageEnabled = false;

    private void scan(IntentIntegrator intent, Collection<String> codeType, String message){
        intent.setDesiredBarcodeFormats(codeType)
                .setPrompt(message)
                .setBeepEnabled(beepEnabled)
                .setBarcodeImageEnabled(barcodeImageEnabled)
                .initiateScan();
    }

    /**
     * Code Scanner Response
     */
    public void codeScanned(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null){
            if (receiver != null){
                receiver.onReceiveCode(result.getContents());
            }
        }
    }

    public void setBeepEnabled(boolean beepEnabled) {
        this.beepEnabled = beepEnabled;
    }

    public void setBarcodeImageEnabled(boolean barcodeImageEnabled) {
        this.barcodeImageEnabled = barcodeImageEnabled;
    }

    /**
     * Create Intent
     */

    private IntentIntegrator getIntent(Activity context){
        return new IntentIntegrator(context);
    }

    private IntentIntegrator getIntent(Fragment context){
        return IntentIntegrator.forSupportFragment(context);
    }

    private IntentIntegrator getIntent(android.app.Fragment context){
        return IntentIntegrator.forFragment(context);
    }

    /**
     * Activity Context
     */
    public void scanQRCode(Activity context, String message){
        scan(getIntent(context), IntentIntegrator.QR_CODE_TYPES, message);
    }

    public void scanBarCode(Activity context, String message){
        scan(getIntent(context), IntentIntegrator.ONE_D_CODE_TYPES, message);
    }

    public void scanAllCodes(Activity context, String message){
        scan(getIntent(context), IntentIntegrator.ALL_CODE_TYPES, message);
    }

    /**
     * SupportFragment Context
     */
    public void scanQRCode(Fragment context, String message){
        scan(getIntent(context), IntentIntegrator.QR_CODE_TYPES, message);
    }

    public void scanBarCode(Fragment context, String message){
        scan(getIntent(context), IntentIntegrator.ONE_D_CODE_TYPES, message);
    }

    public void scanAllCodes(Fragment context, String message){
        scan(getIntent(context), IntentIntegrator.ALL_CODE_TYPES, message);
    }

    /**
     * Fragment Context
     */
    public void scanQRCode(android.app.Fragment context, String message){
        scan(getIntent(context), IntentIntegrator.QR_CODE_TYPES, message);
    }

    public void scanBarCode(android.app.Fragment context, String message){
        scan(getIntent(context), IntentIntegrator.ONE_D_CODE_TYPES, message);
    }

    public void scanAllCodes(android.app.Fragment context, String message){
        scan(getIntent(context), IntentIntegrator.ALL_CODE_TYPES, message);
    }

    /**
     * Interface
     */
    public interface CodeReceiver{
        void onReceiveCode(String scannedMessage);
    }

    private CodeReceiver receiver;

    public void setReceiver(CodeReceiver receiver) {
        this.receiver = receiver;
    }
}