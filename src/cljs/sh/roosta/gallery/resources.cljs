(ns sh.roosta.gallery.resources)

(defn thumbnail-height
  []
  (+ (rand-int 200) 150))

(def items
  [{:id 0 :w 411 :h 664 :th (thumbnail-height) :title "Baby" :src "/v1478438445/gallery/baby.jpg" :category :painting :desc "Oil painting of some baby, attempting a diverse colorscheme"}
   {:id 1 :w 993 :h 715 :th (thumbnail-height) :title "Baptism" :src "/v1478438445/gallery/baptism.jpg" :category :painting :desc "Watercolor baptism scene"}
   {:id 2 :w 1280 :h 562 :th (thumbnail-height) :title "Cake" :src "/v1478438445/gallery/cake.jpg" :category :design :desc "One of several design suggestions for a webpage header"}
   {:id 3 :w 549 :h 908 :th (thumbnail-height) :title "Charcoal Nomad" :src "/v1478438445/gallery/charnom.jpg" :category :drawing :desc "Charcoal masked nomad figure"}
   {:id 4 :w 667 :h 975 :th (thumbnail-height) :title "Cloak in Motion" :src "/v1478438445/gallery/cloak.jpg" :category :photo :desc "One is a series of dark room photographs experimenting with various light exposure settings during development"}
   {:id 5 :w 270 :h 123 :th (thumbnail-height) :gif true :title "Conch" :src "/v1478438445/gallery/conch.gif" :category :pixel :desc "Enemy design for an unfinished untitled shmup game"}
   {:id 6 :w 819 :h 1386 :th (thumbnail-height) :title "Post cover" :src "/v1478438445/gallery/cover.jpg" :category :painting :desc "Conseptual cover for a comic book that was never finished"}
   {:id 7 :w 437 :h 736 :th (thumbnail-height) :title "Kanal Session Design" :src "/v1478438445/gallery/cs.jpg" :category :design :desc "One of several design suggestions for a skating event. Not used but I still like it"}
   {:id 8 :w 1164 :h 785 :th (thumbnail-height) :title "Drainpipes" :src "/v1478438445/gallery/drainpipes.jpg" :category :photo :desc "Dark room development"}
   {:id 9 :w 1525 :h 1084 :th (thumbnail-height) :title "Dunes" :src "/v1478438445/gallery/dunes.jpg" :category :painting :desc "Sand dunes abstracted"}
   {:id 10 :w 376 :h 520 :th (thumbnail-height) :title "Masked Pencil Sketch" :src "/v1478438445/gallery/edn.jpg" :category :drawing :desc "More pencil drawings of a gass mask wearing figure"}
   {:id 11 :w 242 :h 208 :th (thumbnail-height) :gif true :title "Pixel Explosion" :src "/v1478438445/gallery/expl.gif" :category :pixel :desc "Explosion from an unfinished shmup game"}
   {:id 12 :w 184 :h 358 :th (thumbnail-height) :title "Floating Island" :src "/v1478438445/gallery/flisland.png" :category :pixel :desc "Floating island for an unfinished shmup game"}
   {:id 13 :w 1193 :h 929 :th (thumbnail-height) :title "Development fluid experiment" :src "/v1478438445/gallery/fluid.jpg" :category :photo :desc "Messing around with development fluid that turned out kinda neat"}
   {:id 14 :w 1068 :h 1068 :th (thumbnail-height) :title "Goat Boys LP cover design" :src "/v1478438445/gallery/gbcover.jpg" :category :design :desc "Cover design for Goat Boys debut album" :link "https://open.spotify.com/artist/2GadG4cFR4bNH6fyLFt8JK"}
   {:id 15 :w 497 :h 635 :th (thumbnail-height) :title "Ghost Guy" :src "/v1478438445/gallery/ghost.jpg" :category :vector :desc "Early vector attempt at a ghost like creature inspired by anime/pixels and the masks ghosts wear"}
   {:id 16 :w 548 :h 320 :th (thumbnail-height) :title "Gray Brother" :src "/v1478438445/gallery/graybrother.jpg" :category :drawing :desc "Pencil drawing of a gass masked wearing person"}
   {:id 17 :w 1024 :h 576 :th (thumbnail-height) :title "Hooded Moon" :src "/v1478438445/gallery/hoodie.jpg" :category :pixel :desc "Cover suggestion for a band, that was never used"}
   {:id 18 :w 720 :h 470 :th (thumbnail-height) :title "Hufda" :src "/v1478438445/gallery/hufda.jpg" :category :drawing :desc "Cover suggestion for a band, it that was never used"}
   {:id 19 :w 625 :h 859 :th (thumbnail-height) :title "Selv Portrait" :src "/v1478438445/gallery/inkdans.jpg" :category :painting :desc "Selv portrait using ink on plastic"}
   {:id 20 :w 765 :h 959 :th (thumbnail-height) :title "Insectlike" :src "/v1478438445/gallery/insectlike.jpg" :category :drawing :desc "One of several inksect inspired mask drawings"}
   {:id 21 :w 609 :h 924 :th (thumbnail-height) :title "Julie series nr 1" :src "/v1478438445/gallery/js1.jpg" :category :photo :desc "First in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 22 :w 592 :h 869 :th (thumbnail-height) :title "Julie series nr 3" :src "/v1478438445/gallery/js3.jpg" :category :photo :desc "Third in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 23 :w 543 :h 827 :th (thumbnail-height) :title "Julie series nr 4" :src "/v1478438445/gallery/js4.jpg" :category :photo :desc "Forth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 24 :w 574 :h 854 :th (thumbnail-height) :title "Julie series nr 5" :src "/v1478438445/gallery/js5.jpg" :category :photo :desc "Fifth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 25 :w 865 :h 571 :th (thumbnail-height) :title "Julie series nr 6" :src "/v1478438445/gallery/js6.jpg" :category :photo :desc "Sixth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 26 :w 952 :h 1332 :th (thumbnail-height) :title "Kodak Ice" :src "/v1478438445/gallery/kodak.jpg" :category :photo :desc "Failed to develop this macro image of some ice but the result I still like"}
   {:id 27 :w 549 :h 731 :th (thumbnail-height) :title "Desert Lady" :src "/v1478438445/gallery/lady.jpg" :category :painting :desc "Unfinished painting trying to depict a desert-like dress style"}
   {:id 28 :w 480 :h 480 :th (thumbnail-height) :gif true :title "Monoxieman Idle" :src "/v1478438445/gallery/monoxieman_idle.gif" :category :pixel :desc "Idle animation character sprite for unfinished game"}
   {:id 29 :w 480 :h 480 :th (thumbnail-height) :gif true :title "Monoxieman Jump" :src "/v1478438445/gallery/monoxieman_jump.gif" :category :pixel :desc "Jump animation character sprite for unfinished game"}
   {:id 30 :w 480 :h 480 :th (thumbnail-height) :gif true :title "Monoxieman Running" :src "/v1478438445/gallery/monoxieman_running.gif" :category :pixel :desc "Running animation character sprite for unfinished game"}
   {:id 31 :w 592 :h 800 :th (thumbnail-height) :title "More ink!" :src "/v1478438445/gallery/moreink.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 32 :w 592 :h 800 :th (thumbnail-height) :title "More mask!" :src "/v1478438445/gallery/moremask.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 33 :w 1248 :h 1575 :th (thumbnail-height) :title "Nomad Angle" :src "/v1478438445/gallery/nomad-ang.jpg" :category :painting :desc "Titular nomad drawn from an angle. Comic book concept"}
   {:id 34 :w 638 :h 878 :th (thumbnail-height) :title "Nomad Equipped" :src "/v1478438445/gallery/nomad-eq.jpg" :category :drawing :desc "Nomad with equipment, comic book concept"}
   {:id 35 :w 592 :h 725 :th (thumbnail-height) :title "Ouroboros" :src "/v1478438445/gallery/ouroboros.jpg" :category :drawing :desc "Unfinished sketch of ouroboros"}
   {:id 36 :w 752 :h 696 :th (thumbnail-height) :title "Pattern Composition" :src "/v1478438445/gallery/pattern_comp.jpg" :category :drawing :desc "One of many patterns inspired loosely on the art nouveau style"}
   {:id 37 :w 542 :h 769 :th (thumbnail-height) :title "Pray Pencil" :src "/v1478438445/gallery/pray_pencil.jpg" :category :drawing :desc "Sketch of two praying figures"}
   {:id 38 :w 1545 :h 380 :th (thumbnail-height) :title "House gardener logo suggestion" :src "/v1478438445/gallery/precore.png" :category :vector :desc "Logo suggestion for a design/gardening company. Not used but I still like it"}
   {:id 39 :w 598 :h 877 :th (thumbnail-height) :title "Runny ink portrait" :src "/v1478438445/gallery/riportrait.jpg" :category :painting :desc "Self portrait with gratuitous use of ink"}
   {:id 40 :w 1237 :h 1748 :th (thumbnail-height) :title "Salt" :src "/v1478438445/gallery/salt.jpg" :category :painting :desc "Salt/sand dunes abstracted illustration"}
   {:id 41 :w 1184 :h 1600 :th (thumbnail-height) :title "Sectered" :src "/v1478438445/gallery/sectered.jpg" :category :painting :desc "Gass mask wearing kid in the series of insect inspired gass masks"}
   {:id 42 :w 1702 :h 630 :th (thumbnail-height) :title "Shmup Screenshot" :src "/v1478438445/gallery/shmup.png" :category :pixel :desc "Composit unfinished shmup screenshot from an unfinished untitled game"}
   {:id 43 :w 1527 :h 1084 :th (thumbnail-height) :title "Sea of sand" :src "/v1478438445/gallery/sos.jpg" :category :painting :desc "Mountain surrounded by sand. Looks in hindsight more like water"}
   {:id 44 :w 850 :h 1163 :th (thumbnail-height) :title "Splotch" :src "/v1478438445/gallery/splotch.jpg" :category :painting :desc "Watercolor and ink drawing of a gassmask wearing creature"}
   {:id 45 :w 779 :h 566 :th (thumbnail-height) :title "Stones study" :src "/v1478438445/gallery/stones.jpg" :category :painting :desc "Study of stone texture, watercolor"}
   {:id 46 :w 1755 :h 1275 :th (thumbnail-height) :title "Thirst" :src "/v1478438445/gallery/thirst.jpg" :category :painting :desc "Thirsty fellow drawn with ink on rice paper"}
   {:id 47 :w 1755 :h 1275 :th (thumbnail-height) :title "Three men" :src "/v1478438445/gallery/tmen.jpg" :category :painting :desc "Card playing fellows drawn with ink on rice paper"}
   {:id 48 :w 600 :h 600 :th (thumbnail-height) :title "VLN Cover suggestion" :src "/v1478438445/gallery/vln.jpg" :category :pixel :desc "Pixel art cover suggestion for a band album"}
   {:id 49 :w 1224 :h 1228 :th (thumbnail-height) :title "Wind Cover" :src "/v1478438445/gallery/wind.jpg" :category :design :desc "Cover for a 60s inspired jam band. Marker on plastic"}
   {:id 50 :w 542 :h 762 :th (thumbnail-height) :title "Wrap" :src "/v1478438445/gallery/wrap.jpg" :category :drawing :desc "Cloth study, pencil"}
   {:id 51 :w 777 :h 915 :th (thumbnail-height) :title "Paxel" :src "/v1515266655/gallery/paxel.png" :category :pixel :desc "Header for a startpage I made"}
   {:id 52 :w 1228 :h 1332 :th (thumbnail-height) :title "Noir Webpage Splash" :src "/v1478438445/gallery/inkdannoir.jpg" :category :painting :desc "Part of an older web page gallery"}
   {:id 53 :w 1870 :h 1384 :th (thumbnail-height) :title "Pipes" :src "/v1478438445/gallery/pipes.jpg" :category :photo :desc "Dark room experiment"}
   {:id 54 :w 409 :h 545 :th (thumbnail-height) :title "Portrait: Per" :src "/v1478438445/gallery/portrait.jpg" :category :painting :desc "Portrait: Per"}
   {:id 55 :w 192 :h 480 :th (thumbnail-height) :title "Pixel cliff" :src "/v1478438445/gallery/cliff.png" :category :pixel :desc "Pixel Cliff"}

   ])
