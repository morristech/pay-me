package com.github.jberkel.payme;

import com.github.jberkel.payme.listener.OnIabPurchaseFinishedListener;
import com.github.jberkel.payme.model.ItemType;
import com.github.jberkel.payme.model.Purchase;
import org.jetbrains.annotations.Nullable;

import static com.github.jberkel.payme.model.ItemType.UNKNOWN;

class PurchaseFlowState implements OnIabPurchaseFinishedListener {
    static final PurchaseFlowState EMPTY = new PurchaseFlowState(-1, UNKNOWN, null);
    /** The request code used to launch purchase flow */
    final int requestCode;
    /** The item type of the current purchase flow */
    final ItemType itemType;
    /**  The listener registered on launchPurchaseFlow, which we have to call back when the purchase finishes */
    @Nullable
    final OnIabPurchaseFinishedListener listener;

    PurchaseFlowState(int requestCode, ItemType itemType, @Nullable OnIabPurchaseFinishedListener listener) {
        if (itemType == null) throw new IllegalArgumentException("itemType cannot be null");
        this.requestCode = requestCode;
        this.itemType = itemType;
        this.listener = listener;
    }

    @Override
    public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
        if (listener != null) {
            listener.onIabPurchaseFinished(result, purchase);
        }
    }
}