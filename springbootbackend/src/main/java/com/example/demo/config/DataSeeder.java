package com.example.demo.config;

import com.example.demo.model.Shoe;
import com.example.demo.repository.ShoeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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

            // ============================
            // MEN SHOES (20)
            // ============================
            // ============================
// MEN SHOES (20)
// ============================
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
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/fec56838-b7af-4e7c-ba31-0595e7b112bf/nike-just-do-it.jpg",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/d4c8e360-ce0c-4323-8ab9-a3255207b59b/pegasus-trail-5-trail-running-shoes-2jxMFQ.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/b6b18fe6-6c74-49a4-99ea-760cd1862df7/pegasus-trail-5-gore-tex-waterproof-trail-running-shoes-3LbCWQ.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/70317bbc-a9ad-47d7-a090-6c9aed3346a2/juniper-trail-3-trail-running-shoes-b0qq1T.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/50764cdb-a888-40f1-b629-a7a3a3bc37b2/pegasus-trail-5-trail-running-shoes-1NsktP.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/608e2363-c30f-4710-8c98-a5303c8886e2/zegama-2-trail-running-shoes-HkzqFJ.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/5c2f0db0-9338-48ed-9112-bbe60ee32bcf/pegasus-trail-5-trail-running-shoes-1NsktP.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_2.0/h_458,c_limit/03d4f14c-63ec-425b-b2ed-912113d9698f/juniper-trail-2-gore-tex-waterproof-trail-running-shoes-61f57n.png"
            };

// Loop to create 20 men shoes
            for (int i = 0; i < 20; i++) {
                shoes.add(new Shoe(
                        "Nike Men's Shoe " + (i + 1),
                        men,
                        6000 + (i * 150),
                        menImages[i]
                ));
            }



            // ============================
            // WOMEN SHOES (20)
            // ============================
            // ============================
// WOMEN SHOES (20)
// ============================
            String women = "womens-shoes";

            String[] womenImages = {
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/58124f40-8ecb-496b-8842-e902a12efe80/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/542bd5b1-537d-4dcb-8657-a6e2068ad344/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/50588b9d-effc-4f61-b321-82ba20878938/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/6edd029d-bdde-453c-9ec4-ec5eeb820f19/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/f3867f2d-4028-43e4-8f70-b396527dd2e0/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/3d84cd08-fda8-4ff3-a009-96d5d7b2ea96/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/0bffdb32-1c51-42ae-b566-3ce1a8208586/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/33f827fa-dddd-41a0-9172-cbf0d080ce0d/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/c62f9e7c-0646-406b-a857-3fbbdf2b4d14/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/f_auto/dpr_2.0,cs_srgb/h_1496,c_limit/28657490-473a-4c77-90f6-56ba66cc4051/women-s-shoes-clothing-accessories.jpg",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/1ab2393f-b755-4b9b-8215-6228ab8c6a07/WMNS+NIKE+DUNK+LOW.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/3e26df2e-da10-4f05-a2e0-e22372972c0d/WMNS+NIKE+DUNK+LOW.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/1cace6b0-369a-40c5-8dcc-76a0741a2a06/W+NIKE+DUNK+LOW.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/0f76f73e-2578-4d62-abab-c5563ea4f78c/NIKE+DUNK+LOW+RETRO.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/eadd556c-8c80-4775-9684-5bd35e3a1318/NIKE+DUNK+LOW+RETRO+LTD.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/cc9b488c-b1a6-4c35-a687-b38236f82360/W+NIKE+DUNK+LOW+SS.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/d4963f5c-a6a2-41db-bbfb-fd538cb1fe00/NIKE+DUNK+LOW+RETRO+LTD.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/529e9f88-f91c-4d58-87cf-82e21393ea33/NIKE+DUNK+LOW+RETRO+PRM.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/d9eaab9f-5089-47b5-9095-df566497c83e/NIKE+DUNK+LOW+RETRO+LTD+HWN.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/cee94c98-052e-4e0a-a3be-8c170cf02ce2/NIKE+SB+DUNK+LOW+PRO.png"
            };

// Create 20 Women Shoes
            for (int i = 0; i < 20; i++) {
                shoes.add(new Shoe(
                        "Nike Women's Shoe " + (i + 1),
                        women,
                        5500 + (i * 120),
                        womenImages[i]
                ));
            }

            // ============================
            // KIDS SHOES (20)
            // ============================
            // ============================
// KIDS SHOES (20)
// ============================
            // ============================
// KIDS SHOES (SAFE LOOP)
// ============================
            String kids = "kids-shoes";

            String[] kidsImages = {
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/b3bfffb7-3706-44e9-a151-c011a15204c9/AIR+ZOOM+PEGASUS+41+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/fbfa5d70-0650-4f93-80dc-4a47d8aa9bf7/NIKE+P-6000+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/4c61e6e0-b559-4762-883d-cfd3674eef72/AIR+ZOOM+PEGASUS+41+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/bcff28f5-bac6-4fa3-b6f2-03d380782371/AIR+ZOOM+PEGASUS+41+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/c4fab77e-9c45-4a49-a627-45dbe0f19e01/NIKE+P-6000+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/b0ba9376-8a7c-4788-bc50-2254b0ff5b31/AIR+ZOOM+PEGASUS+41+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/d7e96967-42d4-47d4-b20f-e8d8fa338665/NIKE+P-6000+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/2f28d313-e3c7-4987-99b1-2033d6bb12ab/NIKE+PEGASUS+TRAIL+5+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/aa172265-3911-4263-8003-1bfc833453dc/NIKE+P-6000+%28GS%29.png",
                    "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/ad22f4a6-ecf1-455a-b377-7aa4cbf1bb38/NIKE+P-6000+%28GS%29.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.3/h_466,c_limit/7b6ef122-4a07-4295-9f6b-555f554a6380/dunk-low-lego-collection-younger-shoes-o82eyuBV.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.3/h_466,c_limit/5e06a98c-06cf-427a-aa46-940709086c75/hustle-d-12-lego-collection-older-basketball-shoes-pbbLPrA7.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.3/h_466,c_limit/9bd3ef91-2528-4cb3-8728-2e84667b1787/hustle-d-12-lego-collection-younger-shoes-9bH9gdgL.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.3/h_466,c_limit/cb2d64f0-6c95-434f-9394-c87cce7945df/dunk-low-lego-collection-older-shoes-wod8TrIA.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.3/h_466,c_limit/749c362a-1acc-499e-ab5a-43ce6de91672/dunk-low-lego-collection-younger-shoes-ED9XUPDH.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.3/h_466,c_limit/b90ba36d-ce8f-4bee-8958-efd171344b68/gt-cut-3-lego-collection-older-basketball-shoes-NPDng2wz.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/9169f5b6-a103-4708-b1da-f1e634a0c4fd/gt-cut-3-lego-collection-younger-shoes-8JzP7WHH.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/6982ed78-ad52-46de-b99e-aa68dbf5bafa/dunk-low-lego-collection-older-shoes-WtheybGj.png",
                    "https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/5e06a98c-06cf-427a-aa46-940709086c75/hustle-d-12-lego-collection-older-basketball-shoes-pbbLPrA7.png"
            };


            for (int i = 0; i < kidsImages.length; i++) {
                shoes.add(new Shoe(
                        "Nike Kids Shoe " + (i + 1),
                        kids,
                        3000 + (i * 80),
                        kidsImages[i]
                ));
            }


            // ============================
            // SNEAKERS (20)
            // ============================
            String snkrs = "sneakers";
            String[] snkrsImages = {
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/52b7d462-8bbf-4e0b-872d-d027f50d6be5/women-s-shox-r4-vivid-orange-and-glacier-blue-ih2343-801-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/2dba3182-3dcb-4371-a0df-5933ec3e8ff3/women-s-shox-r4-hyper-pink-and-atomic-pink-ih2343-600-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/f35ed61e-c378-4730-a2b4-3b3636d064ef/air-jordan-10-charred-grey-and-black-hj6779-001-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/0c7a1e9e-a71d-45be-9ce1-f36c29dff668/air-force-3-low-x-nigo-phantom-and-night-stadium-hq0261-001-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/4776e12d-4b91-448a-878c-9c6b1553691c/kobe-9-low-protro-em-university-red-and-metallic-gold-ih1400-600-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/f8c1cdef-3521-4750-9239-cb80f9542417/dunk-low-off-noir-and-summit-white-io4550-001-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/39570c9f-3e5b-42b9-8ffe-45925bb83dec/air-jordan-12-taxi-ct8013-117-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/e1d27838-485a-4a11-8aaf-03a589b6c554/women-s-air-jordan-11-pearl-ar0715-110-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/130fb71d-d09e-4667-9e2f-f5cc602f1181/dunk-low-x-stranger-things-phantom-and-midnight-navy-ih6766-001-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/e48619f1-ac21-4a30-9015-ffe982f6b1ec/air-max-95-big-bubble-black-im0695-001-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/d8fd5d3b-424b-4145-9906-f7a33e18da16/shox-ride-2-off-noir-and-velvet-brown-io1906-045-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/e73f6b11-bf91-4a53-b743-6b1eace22514/air-max-95-big-bubble-loyal-blue-im0695-400-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/e54610bf-b1dd-4d5e-a368-2e7d7d89b375/women-s-air-rift-team-red-im5739-600-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/8337705c-b785-453f-ae23-f634ebc3afda/baltoro-black-ih4450-001-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/4ec8be99-90dc-4898-b5d5-903e4cfe7479/nike-baltoro-baroque-brown-and-midnight-navy-ih4450-200-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/0aba3dd0-e4da-44b6-a20f-f2b0469c860d/women-s-air-max-muse-x-windowsen-metallic-silver-and-rage-green-ih2120-002-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/fa26e2bf-fa6c-45a4-b2b4-7fc0d214af98/women-s-air-max-muse-x-windowsen-metallic-gold-and-chile-red-iq3838-700-release-date.jpg",
                    "https://static.nike.com/a/images/t_prod_pc/w_960,c_limit,q_auto,f_auto/558b4bd6-bfde-4ed4-912e-f36bf09e8609/women-s-air-superfly-flax-hq9148-200-release-date.jpg"
            };


            for (int i = 0; i < 20; i++) {
                shoes.add(new Shoe(
                        null,
                        "Nike Sneaker " + (i + 1),
                        snkrs,
                        9000 + (i * 175),
                        snkrsImages[i % snkrsImages.length]
                ));
            }


            // ============================
            // RUNNING SHOES (20)
            // ============================
            String running = "running-shoes";
            String[] runningImages = {
                    "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,q_auto/4c273620-d349-4b9f-b4ed-e7ff7c4160a9/zoom-fly-5-mens-road-running-shoes.png",
                    "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,q_auto/8478ce4d-1b5a-4e00-9077-6e49e0abb936/pegasus-40-mens-road-running-shoes.png",
                    "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,q_auto/99e36fc2-52d7-4571-9b3c-aec03d32a6a8/vomero-17-mens-road-running-shoes.png",
            };

            for (int i = 0; i < 20; i++) {
                shoes.add(new Shoe(
                        null,
                        "Nike Running Shoe " + (i + 1),
                        running,
                        8000 + (i * 180),
                        runningImages[i % runningImages.length]
                ));
            }

            // ============================
            // GYM SHOES (20)
            // ============================
            String gym = "gym-shoes";
            String[] gymImages = {
                    "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,q_auto/560e4226-d8c8-49df-b67d-d0f495facac4/metcon-9-mens-training-shoes.png",
                    "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,q_auto/6b9d9241-ebf6-46c8-a03d-2e772316bedb/in-season-tr-13-womens-training-shoes.png",
                    "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,q_auto/d5b2359c-0267-46c1-9a27-df088b9b2e32/free-metcon-5-womens-training-shoes.png",
            };

            for (int i = 0; i < 20; i++) {
                shoes.add(new Shoe(
                        null,
                        "Nike Gym Shoe " + (i + 1),
                        gym,
                        7000 + (i * 160),
                        gymImages[i % gymImages.length]
                ));
            }

            // SAVE ALL
            shoeRepo.saveAll(shoes);
            System.out.println("🔥 Successfully seeded " + shoes.size() + " shoes into MongoDB!");
        };
    }
}
