package com.example.cloudinary.domain.IView;

import com.example.cloudinary.data.source.IFeedDataSouce;


public interface IHomeUseCase {

    void uploadImage(String imaUri, String fileName, int uploadType,
                     IFeedDataSouce.AddProductEvents addProductEvents);

}
