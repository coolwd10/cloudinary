package com.example.cloudinary.domain;

import com.example.cloudinary.data.source.IFeedDataSouce;
import com.example.cloudinary.domain.IView.IHomeUseCase;

import javax.inject.Inject;


public class HomeUseCaseConttroller implements IHomeUseCase {
    IFeedDataSouce mFeedDataSouce;

    @Inject
    public HomeUseCaseConttroller(IFeedDataSouce feedDataSouce) {
        mFeedDataSouce = feedDataSouce;
    }

    @Override
    public void uploadImage(String imaUri,String fileName,int uploadType,IFeedDataSouce.AddProductEvents addProductEvents) {
        mFeedDataSouce.addProduct(imaUri, fileName,uploadType,addProductEvents);
    }
}
