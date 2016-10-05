(ns sh.roosta.gallery.resources)

(def items
  [
   {:id 1 :w 411 :h 664 :title "Baby" :src "img/baby.jpg" :category :painting :desc "Oil painting of some baby, attempting a diverse colorscheme"}
   {:id 2 :w 993 :h 715 :title "Baptism" :src "img/baptism.jpg" :category :painting :desc "Watercolor baptism scene"}
   {:id 3 :w 1280 :h 562 :title "Cake" :src "img/cake.jpg" :category :design :desc "One of several design suggestions for a webpage header"}
   {:id 4 :w 466 :h 475 :title "Capucha" :src "img/capucha.jpg" :category :vector :desc "Inspired by anime ghost figures, early vector attempt"}
   {:id 5 :w 549 :h 908 :title "Charcoal Nomad" :src "img/charnom.jpg" :category :design :desc "Charcoal masked nomad figure"}
   {:id 6 :w 667 :h 975 :title "Cloak in Motion" :src "img/cloak.jpg" :category :photo :desc "One is a series of dark room photographs experimenting with various light exposure settings during development"}
   {:id 7 :w 640 :h 82 :gif true :title "Conch" :src "img/conch.gif" :category :game :desc "Enemy design for an unfinished untitled shmup game"}
   {:id 8 :w 819 :h 1386 :title "Post cover" :src "img/cover.jpg" :category :painting :desc "Conseptual cover for a comic book that was never finished"}
   {:id 9 :w 437 :h 736 :title "Kanal Session Design" :src "img/cs.jpg" :category :design :desc "One of several design suggestions for a skating event. Not used but I still like it"}
   {:id 10 :w 1164 :h 785 :title "Drainpipes" :src "img/drainpipes.jpg" :category :photo :desc "Dark room development"}
   {:id 11 :w 1525 :h 1084 :title "Dunes" :src "img/dunes.jpg" :category :painting :desc "Sand dunes abstracted"}
   {:id 12 :w 376 :h 520 :title "Masked Pencil Sketch" :src "img/edn.jpg" :category :drawing :desc "More pencil drawings of a gass mask wearing figure"}
   {:id 13 :w 242 :h 208 :gif true :title "Pixel Explosion" :src "img/expl.gif" :category :game :desc "Explosion from an unfinished shmup game"}
   {:id 14 :w 184 :h 358 :title "Floating Island" :src "img/flisland.png" :category :game :desc "Floating island for an unfinished shmup game"}
   {:id 15 :w 1193 :h 929 :title "Development fluid experiment" :src "img/fluid.jpg" :category :photo :desc "Messing around with development fluid that turned out kinda neat"}
   {:id 16 :w 1068 :h 1068 :title "Goat Boys LP cover design" :src "img/gbcover.jpg" :category :design :desc "Cover design for Goat Boys debut album" :link "https://open.spotify.com/artist/2GadG4cFR4bNH6fyLFt8JK"}
   {:id 17 :w 497 :h 635 :title "Ghost Guy" :src "img/ghost.jpg" :category :vector :desc "Early vector attempt at a ghost like creature inspired by anime/games and the masks ghosts wear"}
   {:id 18 :w 548 :h 320 :title "Gray Brother" :src "img/graybrother.jpg" :category :drawing :desc "Pencil drawing of a gass masked wearing person"}
   {:id 19 :w 1024 :h 576 :title "Hooded Moon" :src "img/hoodie.jpg" :category :design :desc "Cover suggestion for a band, that was never used"}
   {:id 20 :w 720 :h 470 :title "Hufda" :src "img/hufda.jpg" :category :design :desc "Cover suggestion for a band, it that was never used"}
   {:id 22 :w 625 :h 859 :title "Selv Portrait" :src "img/inkdans.jpg" :category :painting :desc "Selv portrait using ink on plastic"}
   {:id 23 :w 765 :h 959 :title "Insectlike" :src "img/insectlike.jpg" :category :drawing :desc "One of several inksect inspired mask drawings"}
   {:id 24 :w 609 :h 924 :title "Julie series nr 1" :src "img/js1.jpg" :category :photo :desc "First in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 26 :w 592 :h 869 :title "Julie series nr 3" :src "img/js3.jpg" :category :photo :desc "Third in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 27 :w 543 :h 827 :title "Julie series nr 4" :src "img/js4.jpg" :category :photo :desc "Forth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 28 :w 574 :h 854 :title "Julie series nr 5" :src "img/js5.jpg" :category :photo :desc "Fifth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 29 :w 865 :h 571 :title "Julie series nr 6" :src "img/js6.jpg" :category :photo :desc "Sixth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 30 :w 952 :h 1332 :title "Kodak Ice" :src "img/kodak.jpg" :category :photo :desc "Failed to develop this macro image of some ice but the result I still like"}
   {:id 31 :w 549 :h 731 :title "Desert Lady" :src "img/lady.jpg" :category :painting :desc "Unfinished painting trying to depict a desert-like dress style"}
   {:id 32 :w 489 :h 741 :title "Candle lamp" :src "img/lamp.jpg" :category :diy :desc "Lamp made for christmas using laser cutter, paint and patience. See link for vector blueprint" :link "https://roosta.sh/gallery/img/lamp_vector.png"}
   {:id 33 :w 480 :h 480 :title "Monoxieman Idle" :src "img/monoxieman_idle.gif" :category :game :desc "Idle animation character sprite for unfinished game"}
   {:id 34 :w 480 :h 480 :title "Monoxieman Jump" :src "img/monoxieman_jump.gif" :category :game :desc "Jump animation character sprite for unfinished game"}
   {:id 35 :w 480 :h 480 :title "Monoxieman Running" :src "img/monoxieman_running.gif" :category :game :desc "Running animation character sprite for unfinished game"}
   {:id 36 :w 592 :h 800 :title "More ink!" :src "img/moreink.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 37 :w 592 :h 800 :title "More mask!" :src "img/moremask.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 38 :w 1248 :h 1575 :title "Nomad Angle" :src "img/nomad-ang.jpg" :category :painting :desc "Titular nomad drawn from an angle. Comic book concept"}
   {:id 39 :w 638 :h 878 :title "Nomad Equipped" :src "img/nomad-eq.jpg" :category :drawing :desc "Nomad with equipment, comic book concept"}
   {:id 40 :w 592 :h 725 :title "Ouroboros" :src "img/ouroboros.jpg" :category :drawing :desc "Unfinished sketch of ouroboros"}
   {:id 41 :w 752 :h 696 :title "Pattern Composition" :src "img/pattern_comp.jpg" :category :drawing :desc "One of many patterns inspired loosely on the art nouveau style"}
   {:id 42 :w 542 :h 769 :title "Pray Pencil" :src "img/pray_pencil.jpg" :category :drawing :desc "Sketch of two praying figures"}
   {:id 43 :w 1545 :h 380 :title "House gardener logo suggestion" :src "img/precore.png" :category :vector :desc "Logo suggestion for a design/gardening company. Not used but I still like it"}
   {:id 44 :w 598 :h 877 :title "Runny ink portrait" :src "img/riportrait.jpg" :category :painting :desc "Self portrait with gratuitous use of ink"}
   {:id 45 :w 1237 :h 1748 :title "Salt" :src "img/salt.jpg" :category :painting :desc "Salt/sand dunes abstracted illustration"}
   {:id 46 :w 1184 :h 1600 :title "Sectered" :src "img/sectered.jpg" :category :painting :desc "Gass mask wearing kid in the series of insect inspired gass masks"}
   {:id 47 :w 851 :h 315 :title "Shmup Screenshot" :src "img/shmup.png" :category :game :desc "Composit unfinished shmup screenshot from an unfinished untitled game"}
   {:id 48 :w 1527 :h 1084 :title "Sea of sand" :src "img/sos.jpg" :category :painting :desc "Mountain surrounded by sand. Looks in hindsight more like water"}
   {:id 49 :w 850 :h 1163 :title "Splotch" :src "img/splotch.jpg" :category :painting :desc "Watercolor and ink drawing of a gassmask wearing creature"}
   {:id 50 :w 779 :h 566 :title "Stones study" :src "img/stones.jpg" :category :painting :desc "Study of stone texture, watercolor"}
   {:id 51 :w 1755 :h 1275 :title "Thirst" :src "img/thirst.jpg" :category :painting :desc "Thirsty fellow drawn with ink on rice paper"}
   {:id 52 :w 1755 :h 1275 :title "Three men" :src "img/tmen.jpg" :category :painting :desc "Card playing fellows drawn with ink on rice paper"}
   {:id 53 :w 600 :h 600 :title "VLN Cover suggestion" :src "img/vln.jpg" :category :design :desc "Pixel art cover suggestion for a band album"}
   {:id 54 :w 1224 :h 1228 :title "Wind Cover" :src "img/wind.jpg" :category :design :desc "Cover for a 60s inspired jam band. Marker on plastic"}
   {:id 55 :w 542 :h 762 :title "Wrap" :src "img/wrap.jpg" :category :drawing :desc "Cloth study, pencil"}
   {:id 56 :w 586 :h 500 :title "Head" :src "img/head.png" :category :design :desc "Messing around"}
   {:id 57 :w 1228 :h 1332 :title "Noir Webpage Splash" :src "img/inkdannoir.jpg" :category :painting :desc "Part of an older web page gallery"}
   {:id 57 :w 1870 :h 1384 :title "Pipes" :src "img/pipes.jpg" :category :photo :desc "Dark room experiment"}
   {:id 57 :w 409 :h 545 :title "Portrait: Per" :src "img/portrait.jpg" :category :painting :desc "Portrait: Per"}
   ])
