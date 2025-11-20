package com.example.demo.config;

import com.example.demo.model.Shoe;
import com.example.demo.repository.ShoeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initShoes(ShoeRepository shoeRepo) {
        return args -> {

            if (shoeRepo.count() > 0) {
                System.out.println("Shoes already seeded — skipping.");
                return;
            }

            List<Shoe> shoes = new ArrayList<>();
            Random random = new Random();

            // ============================================
            // UTIL — create a shoe with random MRP/discount
            // ============================================
            java.util.function.Function<String[], Shoe> generateShoe =
                    (data) -> {
                        String name = data[0];
                        String category = data[1];
                        String image = data[2];

                        int mrp = 6000 + random.nextInt(6000);               // MRP 6000–12000
                        int discountPercent = 5 + random.nextInt(25);       // 5%–30%
                        int price = mrp - (mrp * discountPercent / 100);    // sale price

                        return new Shoe(name, category, price, mrp, image);
                    };

            // ============================================
            // MEN SHOES (20)
            // ============================================
            String men = "mens-shoes";

            String[] menImages = {
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/03d4f14c-63ec-425b-b2ed-912113d9698f/juniper-trail-2-gore-tex-waterproof-trail-running-shoes-61f57n.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/5c2f0db0-9338-48ed-9112-bbe60ee32bcf/pegasus-trail-5-trail-running-shoes-1NsktP.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/608e2363-c30f-4710-8c98-a5303c8886e2/zegama-2-trail-running-shoes-HkzqFJ.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/50764cdb-a888-40f1-b629-a7a3a3bc37b2/pegasus-trail-5-trail-running-shoes-1NsktP.png",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/86de6503-60f3-4057-8801-6c9e5137b164/nike-just-do-it.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/40dc9770-53b2-481e-8efe-3a4bd2f3f3c9/nike-just-do-it.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/2588e343-1440-4c63-9dd4-36c2a551e4e2/nike-just-do-it.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/c4009704-4d0d-454b-a6e3-2aa932128a7c/nike-just-do-it.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/1ca9b7aa-cd9f-4d2e-93ad-6085270c1fd0/nike-just-do-it.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/5b270984-2818-49e3-b32c-ba5331b84c90/nike-just-do-it.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/eed31a43-31cb-414b-83f3-321537cbbbb9/nike-just-do-it.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/b021b24e-9d51-4d89-9917-6f475ebca415/nike-just-do-it.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/fec56838-b7af-4e7c-ba31-0595e7b112bf/nike-just-do-it.jpg"
            };

            for (int i = 0; i < 20; i++) {
                shoes.add(generateShoe.apply(new String[]{
                        "Nike Men's Shoe " + (i + 1),
                        men,
                        menImages[i % menImages.length]
                }));
            }

            // ============================================
            // WOMEN SHOES (20)
            // ============================================
            String women = "womens-shoes";

            String[] womenImages = {
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/58124f40-8ecb-496b-8842-e902a12efe80/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/542bd5b1-537d-4dcb-8657-a6e2068ad344/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/50588b9d-effc-4f61-b321-82ba20878938/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/6edd029d-bdde-453c-9ec4-ec5eeb820f19/women-s-shoes-clothing-accessories.jpg"
            };

            for (int i = 0; i < 20; i++) {
                shoes.add(generateShoe.apply(new String[]{
                        "Nike Women's Shoe " + (i + 1),
                        women,
                        womenImages[i % womenImages.length]
                }));
            }

            // ============================================
            // KIDS SHOES (20)
            // ============================================
            String kids = "kids-shoes";

            String[] kidsImages = {
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/b3bfffb7-3706-44e9-a151-c011a15204c9/AIR+ZOOM+PEGASUS+41+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/fbfa5d70-0650-4f93-80dc-4a47d8aa9bf7/NIKE+P-6000+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/4c61e6e0-b559-4762-883d-cfd3674eef72/AIR+ZOOM+PEGASUS+41+%28GS%29.png",
            };

            for (int i = 0; i < 20; i++) {
                shoes.add(generateShoe.apply(new String[]{
                        "Nike Kids Shoe " + (i + 1),
                        kids,
                        kidsImages[i % kidsImages.length]
                }));
            }

            // ============================================
            // SNEAKERS (20)
            // ============================================
            String snkrs = "sneakers";

            String[] snkrsImages = {
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/52b7d462-8bbf-4e0b-872d-d027f50d6be5/women-s-shox-r4-vivid-orange-and-glacier-blue-ih2343-801-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/2dba3182-3dcb-4371-a0df-5933ec3e8ff3/women-s-shox-r4-hyper-pink-and-atomic-pink-ih2343-600-release-date.jpg"
            };

            for (int i = 0; i < 20; i++) {
                shoes.add(generateShoe.apply(new String[]{
                        "Nike Sneaker " + (i + 1),
                        snkrs,
                        snkrsImages[i % snkrsImages.length]
                }));
            }

            // ============================================
            // RUNNING SHOES (20)
            // ============================================
            String running = "running-shoes";

            String[] runningImages = {
                    "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,q_auto/4c273620-d349-4b9f-b4ed-e7ff7c4160a9/zoom-fly-5-mens-road-running-shoes.png",
                    "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,q_auto/8478ce4d-1b5a-4e00-9077-6e49e0abb936/pegasus-40-mens-road-running-shoes.png",
            };

            for (int i = 0; i < 20; i++) {
                shoes.add(generateShoe.apply(new String[]{
                        "Nike Running Shoe " + (i + 1),
                        running,
                        runningImages[i % runningImages.length]
                }));
            }

            // ============================================
            // GYM SHOES (20)
            // ============================================
            String gym = "gym-shoes";

            String[] gymImages = {
                    "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,q_auto/560e4226-d8c8-49df-b67d-d0f495facac4/metcon-9-mens-training-shoes.png"
            };

            for (int i = 0; i < 20; i++) {
                shoes.add(generateShoe.apply(new String[]{
                        "Nike Gym Shoe " + (i + 1),
                        gym,
                        gymImages[i % gymImages.length]
                }));
            }

            // SAVE ALL
            shoeRepo.saveAll(shoes);
            System.out.println("🔥 Successfully seeded " + shoes.size() + " shoes into MongoDB!");
        };
    }
}
