package com.ecommerce.controller;

import com.ecommerce.controller.impl.IOrderController;
import com.ecommerce.models.CartProduct;
import com.ecommerce.models.User;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtils;
import com.ecommerce.view.OrderPage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.FileUtil.getFilePath;
import static com.ecommerce.utils.CustomerUtils.getLoggedInUser;
import static com.ecommerce.utils.FileUtil.getOrderPath;
import static com.ecommerce.utils.Utils.println;

public class OrderController implements IOrderController {

    private final HomeController homeController;
    private static OrderPage orderPage;



    public OrderController(HomeController homeController)
    {
        this.homeController = homeController;
        orderPage = new OrderPage();

    }

    @Override
    public void checkout() {
        User loggedInUser = getLoggedInUser();

        try {
            FileWriter fileWriter = new FileWriter(getFilePath() + System.currentTimeMillis() + ".txt");
            fileWriter.write("Your Order Products are:");
            fileWriter.write("\n");

            double total = 0;
            for (CartProduct cartProduct : loggedInUser.getUserCart().getCartProducts()) {
                total += cartProduct.getCount() * cartProduct.getProduct().getPrice();
                fileWriter.write(cartProduct.getProduct().getTitle() + " x " + cartProduct.getCount() + " = Rs. " + cartProduct.getProduct().getPrice() * cartProduct.getCount());
                fileWriter.write("\n");
            }
            fileWriter.write("Total - ₹ " + total);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLoggedInUser().setUserCart(null);
        orderPage.printSuccess();
        homeController.printList();
    }

    @Override
    public void printOrders() {
        Map<String, String> files = listFilesForFolder(new File(getFilePath()));
        if (files.isEmpty()) {
            orderPage.printNoOrders();
            homeController.printList();
        } else {
            orderPage.printOrders(files);
            try {
                int orderId = enterInt(StringUtils.ENTER_CHOICE);
                if (orderId == 99) {
                    homeController.printList();
                } else {
                    if (orderId > files.size()) {
                        println(StringUtils.INVALID_CHOICE);
                        printOrders();
                    } else {
                        int id = 1;
                        String path = "";
                        for (final String key : files.keySet()) {
                            if (id == orderId) {
                                path = files.get(key);
                            }
                        }
                        BufferedReader r = new BufferedReader(new FileReader(getFilePath() + path));
                        String line;
                        orderPage.printDesign();
                        while ((line = r.readLine()) != null) {
                            println(line);
                        }
                        printOrders();
                    }
                }

            } catch (AppException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
@Override
    public void viewOrders() {
        try (BufferedReader in = new BufferedReader(new FileReader(getOrderPath()))) {
            String str;
            while ((str = in.readLine()) != null) {
                println("   " + str);
            }
        } catch (FileNotFoundException e) {
            println("File Not Found");
        } catch (IOException e) {
            println("File Read Error");
        }
    }


    private Map<String, String> listFilesForFolder(final File folder) throws RuntimeException {
        Map<String, String> files = new HashMap<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            Path path = new File(getFilePath() + fileEntry.getName()).toPath();
            BasicFileAttributes file_att;
            try {
                file_att = Files.readAttributes(path, BasicFileAttributes.class);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy - ");

                Date d = sdf.parse(file_att.creationTime().toString());

                if (fileEntry.getName().startsWith(String.valueOf(getLoggedInUser().getId())))
                    files.put(dateFormat.format(d), fileEntry.getName());
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }

        }
        return files;
    }

}
